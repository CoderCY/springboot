package cn.cy.service.impl;

import cn.cy.dao.inter.BasicConfigInter;
import cn.cy.service.inter.BasicConfigServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018\4\17 0017.
 */
@Service
public class BasicConfigServiceImpl implements BasicConfigServiceInter {

    @Autowired
    private BasicConfigInter basicConfigInter;

    @Override
    public String getValue(Map<String, String> map) {
        /**
         * 准备可能会出现的业务逻辑
         */
        return basicConfigInter.getValue(map);
    }
}
