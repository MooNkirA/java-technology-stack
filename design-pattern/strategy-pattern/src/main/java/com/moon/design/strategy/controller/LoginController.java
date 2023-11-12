package com.moon.design.strategy.controller;

import com.moon.design.strategy.model.LoginReq;
import com.moon.design.strategy.model.LoginResp;
import com.moon.design.strategy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆控制层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 12:04
 * @description
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResp login(@RequestBody LoginReq loginReq) throws InterruptedException {
        if (loginReq.getType().equals("abc")) {
            log.error("没有这种登录方式:{}", loginReq.getType());
        }
        if (loginReq.getType().equals("123")) {
            throw new RuntimeException("错误的登录方式");
        }

        return userService.login(loginReq);
    }

}
