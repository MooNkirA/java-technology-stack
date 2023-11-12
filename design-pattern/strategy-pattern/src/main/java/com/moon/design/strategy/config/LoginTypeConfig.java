package com.moon.design.strategy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 自定义配置读取类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 12:07
 * @description
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "login")
public class LoginTypeConfig {

    private Map<String, String> types;

}
