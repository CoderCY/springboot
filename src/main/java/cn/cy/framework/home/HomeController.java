package cn.cy.framework.home;

import cn.cy.dao.inter.UrlConfigDaoInter;
import cn.cy.service.inter.BasicConfigServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018\4\16 0016.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private BasicConfigServiceInter basicConfigServiceImpl;

    @Autowired
    private UrlConfigDaoInter urlConfigDaoInter;

    @RequestMapping(value = "/{id}/{key}/index", method = RequestMethod.GET)
    public String index(@PathVariable("id") String id, @PathVariable("key") String key, Model model){
        String view = "index";
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("key", key);
        view = basicConfigServiceImpl.getValue(map);
        List<Map<String, String>> navList = urlConfigDaoInter.urlNavList(id);
        model.addAttribute("navList", navList);
        return view;
    }
}
