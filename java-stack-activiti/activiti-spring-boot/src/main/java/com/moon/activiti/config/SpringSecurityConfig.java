package com.moon.activiti.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-16 16:58
 * @description
 */
@Configuration
public class SpringSecurityConfig {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityConfig.class);

    /**
     * 创建 UserDetailsService 实例。用于定义用户信息服务（查询用户信息）
     * 此示例为了方便，直接硬编码用户信息并加入到内存中
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // 创建用户内存管理器
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        // 构造用户的信息。（后面处理流程时用到的任务负责人，需要添加在这里）
        String[][] usersGroupAndRoles = {
                {"jack", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"rose", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"tom", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"jerry", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"other", "password", "ROLE_ACTIVITI_USER", "GROUP_otherTeam"},
                {"system", "password", "ROLE_ACTIVITI_USER"},
                {"admin", "password", "ROLE_ACTIVITI_ADMIN"}
        };

        for (String[] users : usersGroupAndRoles) {
            // 用户的角色和组
            List<String> authStrList = Arrays.asList(Arrays.copyOfRange(users, 2, users.length));

            LOGGER.info("> Registering new user: {} with the following Authorities[{}]", users[0], authStrList);

            User user = new User(users[0], passwordEncoder().encode(users[1]),
                    authStrList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            // 将用户信息加载到内存中
            inMemoryUserDetailsManager.createUser(user);
        }
        return inMemoryUserDetailsManager;

    }

    /**
     * 密码编码器，即设置登陆时密码的校验
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
