package cn.cy.dao.inter;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by Administrator on 2018\4\17 0017.
 */
@Mapper
public interface BasicConfigInter {

    @Select("select value from cy_basic_config where ptid=#{id} and key=#{key}")
    String getValue(Map<String, String> map);
}
