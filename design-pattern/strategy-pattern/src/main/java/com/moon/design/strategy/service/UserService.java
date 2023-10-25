package com.moon.design.strategy.service;

import com.moon.design.strategy.model.LoginReq;
import com.moon.design.strategy.model.LoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 12:06
 * @description
 */
@Service
public class UserService {

    @Autowired
    private UserLoginFactory factory;

    public LoginResp login(LoginReq loginReq) {
        UserGranter granter = factory.getGranter(loginReq.getType());
        if (granter == null) {
            LoginResp loginResp = new LoginResp();
            loginResp.setSuccess(false);
            return loginResp;
        }
        LoginResp resp = granter.login(loginReq);
        resp.setSuccess(true);
        return resp;
    }
}
