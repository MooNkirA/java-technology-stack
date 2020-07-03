package com.moon.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Netty 框架学习专用工程主入口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-12-15 09:33
 * @description
 */
@SpringBootApplication(scanBasePackages = {"com.moon.netty"})
public class NettyApp {

    public static void main(String[] args) {
        SpringApplication.run(NettyApp.class, args);
    }

}
