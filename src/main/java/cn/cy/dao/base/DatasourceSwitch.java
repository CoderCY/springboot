package cn.cy.dao.base;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\3\18 0018.
 */

public class DatasourceSwitch {

    @Autowired
    private Environment ev;

    private static Map<String, SqlSessionFactory> SQL_SESSION_FACTORYS;

    static {
        SQL_SESSION_FACTORYS = new HashMap<String, SqlSessionFactory>();
    }

    /**
     * 提供数据源参数，不提供 提交 参数（适用于查询操作）
     * @param clazz
     * @param dbName
     * @return
     */
    public <T> T getDao(Class<T> clazz,String dbName){
        return getDao(clazz, dbName, false);
    }

    /**
     * 提供数据源参数，提供 提交 参数（适用于增删改查）
     * @param clazz
     * @param dbName
     * @param commit
     * @return
     */
    public synchronized  <T> T  getDao(Class<T> clazz,String dbName,boolean commit){
        SqlSessionFactory sessionFactory = SQL_SESSION_FACTORYS.get(dbName);
        if (sessionFactory==null) {
            DataSource dataSource = getDataSource(dbName);
            try {
                sessionFactory = getSqlSessionFactory(dataSource);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQL_SESSION_FACTORYS.put(dbName, sessionFactory);
        }

        if (!sessionFactory.getConfiguration().hasMapper(clazz)) {
            sessionFactory.getConfiguration().addMapper(clazz);
        }

        SqlSession sqlSession = sessionFactory.openSession();
        T t =sqlSession.getMapper(clazz);
        return new MapperProxy<T>(t, sqlSession,commit).getInstance();
    }

    //获取从库数据源信息
    private DataSource getDataSource(String dbName) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String driverClass = ev.getProperty("jdbc.datasource."+dbName+".driverClassName");
        String url = ev.getProperty("jdbc.datasource."+dbName+".url");
        String userName = ev.getProperty("jdbc.datasource."+dbName+".username");
        String passWord = ev.getProperty("jdbc.datasource."+dbName+".password");
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        return dataSource;
    }

    //获取SqlSessionFactory(会话工厂)
    private SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        String mapperLocations = ev.getProperty("slaveMappers.mapperLocations");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return bean.getObject();
    }
    /**
     * @param <T>
     */
    private class MapperProxy<T> implements InvocationHandler {
        private T mapper;
        private SqlSession sqlSession;
        private boolean commit;

        private MapperProxy(T mapper, SqlSession sqlSession,boolean commit) {
            this.mapper = mapper;
            this.sqlSession = sqlSession;
            this.commit=commit;
        }

        @SuppressWarnings("unchecked")
        private T getInstance() {
            return (T) Proxy.newProxyInstance(mapper.getClass().getClassLoader(),
                    mapper.getClass().getInterfaces(), new MapperProxy<T>(mapper, sqlSession,commit));
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object object = null;
            try {
                object = method.invoke(mapper, args);
                if (commit) {
                    sqlSession.commit();
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                sqlSession.close();
            }
            return object;
        }
    }

}
