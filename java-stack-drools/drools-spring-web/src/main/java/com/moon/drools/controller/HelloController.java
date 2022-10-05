package com.moon.drools.controller;

import com.moon.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试请求控制层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-09-30 22:43
 * @description
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/rule")
    public String rule() {
        ruleService.rule();
        return "OK";
    }

}

