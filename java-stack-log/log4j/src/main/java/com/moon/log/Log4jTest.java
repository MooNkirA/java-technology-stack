package com.moon.log;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Log4j 日志测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-18 8:54
 * @description
 */
public class Log4jTest {

    // 基础使用测试
    @Test
    public void testBasic() {
        // 若无 Log4j 的配置文件，可以使用编程方式来初始化配置信息
        // BasicConfigurator.configure();

        // 获取日志记录器对象
        Logger logger = Logger.getLogger(Log4jTest.class);

        // 输出不同日志级别的日志
        logger.fatal("fatal"); // 严重错误，一般会造成系统崩溃并终止运行
        logger.error("error"); // 错误信息，不会影响系统运行
        logger.warn("warn");   // 警告信息，可能会发生问题
        logger.info("info");   // 运行信息，数据连接、网络连接、IO 操作等等
        logger.debug("debug"); // 调试信息，一般在开发中使用，记录程序变量参数传递信息等等
        logger.trace("trace"); // 追踪信息，记录程序所有的流程信息
    }

    // 自定义的 Logger 测试
    @Test
    public void testCustomLogger() {
        // 自定义 com.moon.log 的 logger
        Logger logger = Logger.getLogger(Log4jTest.class);

        // 输出不同日志级别的日志
        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");

        // 自定义 org.apache 的 logger
        Logger logger2 = Logger.getLogger(Logger.class);
        logger2.fatal("fatal logger2");
        logger2.error("error logger2");
        logger2.warn("warn logger2");
        logger2.info("info logger2");
        logger2.debug("debug logger2");
        logger2.trace("trace logger2");
    }

}
