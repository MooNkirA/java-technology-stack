package com.moon.design.strategy.model;

import lombok.Data;

/**
 * 请求参数
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 12:02
 * @description
 */
@Data
public class LoginReq {

    private String name;
    private String password;
    private String phone;
    private String validateCode;//手机验证码
    private String wxCode;//用于微信登录
    /**
     * account : 用户名密码登录
     * sms : 手机验证码登录
     * we_chat : 微信登录
     */
    private String type;
}

