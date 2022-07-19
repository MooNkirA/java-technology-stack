package com.moon.gradle.controller;

import com.moon.gradle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public String save(Model model) {
        String save = userService.save();
        model.addAttribute("save", save);
        return "success";
    }

}
