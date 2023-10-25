package com.moon.design.strategy.service;

import com.moon.design.strategy.config.LoginTypeConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 操作策略的上下文环境类/工具类。
 * 将策略整合起来，方便管理
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 14:39
 * @description
 */
@Component
public class UserLoginFactory implements ApplicationContextAware {

    private static final Map<String, UserGranter> granterPool = new ConcurrentHashMap<>();

    @Autowired
    private LoginTypeConfig loginTypeConfig;

    /**
     * 从配置文件中读取策略信息存储到map中
     * { account:accountGranter, sms:smsGranter, we_chat:weChatGranter }
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        loginTypeConfig.getTypes().forEach((k, v) -> {
            granterPool.put(k, (UserGranter) applicationContext.getBean(v));
        });
    }

    /**
     * 对外提供获取具体策略
     *
     * @param grantType 用户的登录方式，需要跟配置文件中匹配
     * @return 具体策略
     */
    public UserGranter getGranter(String grantType) {
        return granterPool.get(grantType);
    }
}
