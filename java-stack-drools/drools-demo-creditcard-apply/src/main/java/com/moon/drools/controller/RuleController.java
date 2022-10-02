package com.moon.drools.controller;

import com.moon.drools.entity.CreditCardApplyInfo;
import com.moon.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求控制层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-09-30 22:43
 * @description
 */
@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/creditCardApply")
    public CreditCardApplyInfo rule(@RequestBody CreditCardApplyInfo creditCardApplyInfo) {
        creditCardApplyInfo = ruleService.creditCardApply(creditCardApplyInfo);
        return creditCardApplyInfo;
    }

}

