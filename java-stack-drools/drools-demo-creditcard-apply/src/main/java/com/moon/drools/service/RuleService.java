package com.moon.drools.service;

import com.moon.drools.entity.CreditCardApplyInfo;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 规则处理业务类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-02 9:33
 * @description
 */
@Service
public class RuleService {

    @Autowired
    private KieBase kieBase;

    // 调用Drools规则引擎实现信用卡申请
    public CreditCardApplyInfo creditCardApply(CreditCardApplyInfo creditCardApplyInfo) {
        KieSession session = kieBase.newKieSession();
        session.insert(creditCardApplyInfo);
        session.fireAllRules();
        session.dispose();
        System.out.println(creditCardApplyInfo);
        return creditCardApplyInfo;
    }
}
