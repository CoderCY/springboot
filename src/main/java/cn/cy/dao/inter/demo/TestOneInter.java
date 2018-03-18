package cn.cy.dao.inter.demo;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018\3\18 0018.
 */
public interface TestOneInter {

    @Select("select * from users")
    List<Map<String, String>> userList(String dbName);

    //使用xml执行sql
    List<Map<String, String>> userTwoList(String dbName);
}
