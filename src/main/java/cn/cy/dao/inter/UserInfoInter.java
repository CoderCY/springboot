package cn.cy.dao.inter;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018\3\18 0018.
 */
@Mapper
public interface UserInfoInter {

    @Select("select * from sys_user")
    List<Map<String, String>> userList();
}
