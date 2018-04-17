//package cn.cy.dao.impl;
//
//import cn.cy.dao.base.DatasourceSwitch;
//import cn.cy.dao.inter.demo.TestOneInter;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Administrator on 2018\3\18 0018.
// */
//@Repository
//public class UserInfoRepository extends DatasourceSwitch implements TestOneInter {
//
//    @Override
//    public List<Map<String, String>> userList(String dbName) {
//        return getDao(TestOneInter.class,dbName).userList(dbName);
//    }
//
//    @Override
//    public List<Map<String, String>> userTwoList(String dbName) {
//        return getDao(TestOneInter.class,dbName).userTwoList(dbName);
//    }
//}
