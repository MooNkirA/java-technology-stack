package com.moon.drools.controller;

import com.moon.drools.entity.Calculation;
import com.moon.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/calculate")
    public Calculation rule(double wage) {
        Calculation calculation = new Calculation();
        calculation.setWage(wage);
        calculation = ruleService.calculate(calculation);
        return calculation;
    }

}

