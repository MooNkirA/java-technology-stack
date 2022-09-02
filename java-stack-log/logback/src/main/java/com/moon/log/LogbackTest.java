package com.moon.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logback 日志测试，并且不提供配置文件而使用其提供的默认配置。
 * 注意：如需要测试默认配置时，先将 resources 目录下的 logback 配置文件失效。
 * 将 logback.xml 与 logback-access.xml 文件分别修改为 logback.xml.bak 与 logback-access.xml.bak
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-18 23:03
 * @description
 */
public class LogbackTest {
    // 创建日志记录对象
    public static final Logger LOGGER = LoggerFactory.getLogger(LogbackTest.class);

    // 简单使用
    @Test
    public void test1() {
        LOGGER.debug("debug ...");
    }

    // 打印日志内部状态
    @Test
    public void test2() {
        LOGGER.debug("debug ...");
        // 打印内部的状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }

    /*
     * 日志输出级别：ERROR > WARN > INFO > DEBUG > TRACE
     *  测试默认的日志输出级别
     */
    @Test
    public void test3() {
        // 输出不同级别的日志
        LOGGER.error("error");
        LOGGER.warn("wring");
        LOGGER.info("info");
        // 当前 logger 对象的日志输出默认级别为 debug，从 root logger 继承来的
        LOGGER.debug("debug");
        // 因为默认的输出级别为debug，所以这一条日志不会输出
        LOGGER.trace("trace");
    }

    // 手动设置日志输出级别
    @Test
    public void test4() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LOGGER;
        logger.setLevel(Level.WARN);
        logger.error("error ...");
        logger.warn("warn ...");
        logger.info("info ...");
        logger.debug("debug ...");
        logger.trace("trace ...");
    }

    // 测试Logger的继承
    @Test
    public void test5() {
        ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.moon");
        logger.setLevel(Level.INFO);
        logger.error("error ...");
        logger.warn("warn ...");
        logger.info("info ...");
        logger.debug("debug ...");
        logger.trace("trace ...");

        // "com.moon.log" 会继承 "com.moon" 的有效级别
        Logger barLogger = LoggerFactory.getLogger("com.moon.log");
        // 这条日志会打印，因为 INFO >= INFO
        barLogger.info("子级信息");
        // 这条日志不会打印，因为 DEBUG < INFO
        barLogger.debug("子级调试信息");
    }

    // Logger获取，根据同一个名称获得的logger都是同一个实例
    @Test
    public void test6() {
        Logger logger1 = LoggerFactory.getLogger("com.moon.log");
        Logger logger2 = LoggerFactory.getLogger("com.moon.log");
        System.out.println(logger1 == logger2); // 结果：true
    }

    // 参数化日志
    @Test
    public void test7() {
        LOGGER.debug("hello {}", "world"); // 打印：hello world
    }

}
