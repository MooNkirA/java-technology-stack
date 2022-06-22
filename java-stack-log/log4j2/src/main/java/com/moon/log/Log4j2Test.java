package com.moon.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 以 Log4j2 自带的日志门面来实现日志功能
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-20 15:27
 * @description
 */
public class Log4j2Test {

    // 定义日志记录器对象
    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);

    // 基础使用示例
    @Test
    public void testBasic() {
        // 日志消息输出
        LOGGER.fatal("fatal");
        LOGGER.error("error"); // log4j2 默认是级别
        LOGGER.warn("warn");
        LOGGER.info("inf");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

}
