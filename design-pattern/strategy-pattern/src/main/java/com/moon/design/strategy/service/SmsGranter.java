package com.moon.design.strategy.service;

import com.moon.design.strategy.model.LoginReq;
import com.moon.design.strategy.model.LoginResp;
import org.springframework.stereotype.Component;

/**
 * 策略:短信登录
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 14:33
 * @description
 */
@Component
public class SmsGranter implements UserGranter {

    @Override
    public LoginResp login(LoginReq loginReq) {
        System.out.println("策略:登录方式为短信登录");
        // 执行业务操作
        return new LoginResp();
    }

}
