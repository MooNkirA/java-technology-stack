package com.moon.drools.service;

import org.kie.api.KieBase;
import org.kie.api.cdi.KBase;
import org.kie.api.runtime.KieSession;
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

    @KBase("kbase") // 注入KieBase对象
    private KieBase kieBase;

    public void rule() {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
