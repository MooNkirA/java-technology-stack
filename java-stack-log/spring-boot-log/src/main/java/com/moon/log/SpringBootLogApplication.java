package com.moon.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-20 22:50
 * @description
 */
@SpringBootApplication
public class SpringBootLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLogApplication.class, args);

        // 声明日志记录器对象(使用 Slf4j 的日志门面)
        Logger LOGGER = LoggerFactory.getLogger(SpringBootLogApplication.class);
        // 打印日志信息
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info"); // 默认日志级别
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

}
