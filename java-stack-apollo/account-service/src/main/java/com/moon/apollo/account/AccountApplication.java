package com.moon.apollo.account;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 统一账户服务
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-6 11:27
 * @description
 */
@SpringBootApplication
@EnableApolloConfig
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
