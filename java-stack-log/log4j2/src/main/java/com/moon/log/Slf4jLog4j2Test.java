package com.moon.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于 Slf4j 日志门面，Log4j2 作为实现日志功能测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-20 15:41
 * @description
 */
public class Slf4jLog4j2Test {

    // 创建 Slf4j 的日志记录器对象
    public static final Logger LOGGER = LoggerFactory.getLogger(Slf4jLog4j2Test.class);

    // Slf4j + Log4j2 基础使用测试
    @Test
    public void testBasic() {
        // 日志输出
        LOGGER.error("error");  // log4j2 默认是级别
        LOGGER.warn("wring");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

}
