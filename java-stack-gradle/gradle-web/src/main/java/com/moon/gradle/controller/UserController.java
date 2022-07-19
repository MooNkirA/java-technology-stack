package com.moon.gradle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-23 15:59
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/save")
    public String save() {
        return "success";
    }

}
