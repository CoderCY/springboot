package cn.cy.framework.home;

import cn.cy.dao.inter.UrlConfigDaoInter;
import cn.cy.service.inter.BasicConfigServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018\4\16 0016.
 */
@RestController
public class HomeController {

    @Autowired
    private BasicConfigServiceInter basicConfigServiceImpl;

    @Autowired
    private UrlConfigDaoInter urlConfigDaoInter;

    @RequestMapping(value = "/{id}/home", method = RequestMethod.GET)
    public Object index(@PathVariable("id") String id){
        List<Map<String, String>> navList = urlConfigDaoInter.urlNavList(id);
        return navList;
    }
}
