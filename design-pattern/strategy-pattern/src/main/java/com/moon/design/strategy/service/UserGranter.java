package com.moon.design.strategy.service;

import com.moon.design.strategy.model.LoginReq;
import com.moon.design.strategy.model.LoginResp;

/**
 * 抽象策略类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 14:29
 * @description
 */
public interface UserGranter {

    /**
     * 获取数据
     *
     * @param loginReq 传入的参数
     *                 0:账号密码
     *                 1:短信验证
     *                 2:微信授权
     * @return map值
     */
    LoginResp login(LoginReq loginReq);

}
