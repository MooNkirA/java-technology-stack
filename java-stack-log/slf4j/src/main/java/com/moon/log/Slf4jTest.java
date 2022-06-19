package com.moon.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j 日志门面测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-18 15:44
 * @description
 */
public class Slf4jTest {

    // 创建日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);

    // 快速入门
    @Test
    public void testBasic() {
        // 输出不同级别的日志
        LOGGER.error("error");
        LOGGER.warn("wring");
        LOGGER.info("info"); // 默认级别
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        // 使用占位符输出日志信息
        String name = "MooNkirA";
        Integer age = 14;
        LOGGER.info("用户：{},{}", name, age);

        // 将系统的异常信息输出
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            LOGGER.error("出现异常：", e);
        }
    }

}
