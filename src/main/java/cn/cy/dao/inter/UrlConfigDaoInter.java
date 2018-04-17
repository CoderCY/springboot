package cn.cy.dao.inter;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018\4\17 0017.
 */
@Mapper
public interface UrlConfigDaoInter {

    @Select("select * from cy_url_config where ptid=#{id} and kind='B' ORDER BY orderby")
    List<Map<String, String>> urlNavList(@Param("id") String id);
}
