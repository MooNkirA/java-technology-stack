package com.moon.drools.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 规则业务层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-09-30 22:44
 * @description
 */
@Service
public class RuleService {

    // 此处直接注入即可，在配置类中已经创建并且加入到容器中
    @Autowired
    private KieBase kieBase;

    public void rule() {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
