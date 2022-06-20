package com.moon.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logback 日志测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-18 23:03
 * @description
 */
public class LogbackTest {
    // 创建日志记录对象
    public static final Logger LOGGER = LoggerFactory.getLogger(LogbackTest.class);

    // 基础使用测试
    @Test
    public void testBasic() {
        // 输出不同级别的日志
        LOGGER.error("error");
        LOGGER.warn("wring");
        LOGGER.info("info");
        LOGGER.debug("debug"); // 默认级别
        LOGGER.trace("trace");
    }

}
