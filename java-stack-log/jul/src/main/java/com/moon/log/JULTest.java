package com.moon.log;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * JUL (Java util Logging) 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-17 18:40
 * @description
 */
public class JULTest {

    // 基础使用
    @Test
    public void testBasic() {
        // 1. 创建日志记录器对象，一般使用当前类的全路径名称
        Logger logger = Logger.getLogger(JULTest.class.getName());
        // 2. 使用不同日志级别的方法，输出日志记录
        logger.info("hello jul");
        /*
         * 使用通用方法进行日志记录
         *  public void log(Level level, String msg)
         *      level 参数：日志级别
         *      msg 参数：日志内容
         */
        logger.log(Level.INFO, "info msg");

        // 通过占位符方式，输出变量值
        String name = "MooNkirA";
        int age = 13;
        logger.log(Level.INFO, "用户信息：{0},{1}", new Object[]{name, age});
    }

    // 测试默认的日志级别
    @Test
    public void testDefaultLevel() {
        // 1. 创建日志记录器对象，一般使用当前类的全路径名称
        Logger logger = Logger.getLogger(JULTest.class.getName());
        // 2. 使用不同日志级别的方法，输出日志记录
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info"); // 默认日志输出级别，默认无法输出后面更低级别的日志
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    // 测试自定义日志级别
    @Test
    public void testConfigLevel() {
        // 1. 创建日志记录器对象，一般使用当前类的全路径名称
        Logger logger = Logger.getLogger(JULTest.class.getName());

        // 2. 关闭系统默认配置
        logger.setUseParentHandlers(false);

        // 3. 自定义配置日志级别
        // 3.1 创建控制台输出对象 ConsoleHandler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // 3.2 创建简单格式转换对象 SimpleFormatter
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // 3.4 Handler 关联 Formatter
        consoleHandler.setFormatter(simpleFormatter);
        // 3.5 日志记录器关联 Handler
        logger.addHandler(consoleHandler);

        // 3.6 配置日志具体级别
        logger.setLevel(Level.ALL); // 设置全局日志级别
        consoleHandler.setLevel(Level.FINE); // 设置具体的 Handler 级别
        // 注意，最终的日志级别，以设置中最高的级别为准

        // 4. 使用不同日志级别的方法，输出日志记录
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    // 测试日志记录输出到文件
    @Test
    public void testConfigLogFile() throws IOException {
        // 1. 创建日志记录器对象，一般使用当前类的全路径名称
        Logger logger = Logger.getLogger(JULTest.class.getName());

        // 2. 指定日志输出到文件
        // 2.1 创建日志文件输出对象 FileHandler
        FileHandler fileHandler = new FileHandler("E:/logs/jul.log");
        // 2.2 创建简单格式转换对象 SimpleFormatter
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // 2.4 Handler 关联 Formatter
        fileHandler.setFormatter(simpleFormatter);
        // 2.5 日志记录器关联 Handler
        logger.addHandler(fileHandler);

        // 3. 使用不同日志级别的方法，输出日志记录
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    // 测试 Logger 之间的父子关系
    @Test
    public void testLogParent() {
        // 1. 创建两个不同层级的日志记录器
        Logger logger1 = Logger.getLogger("com.moon.log");
        Logger logger2 = Logger.getLogger("com.moon");

        // 测试 logger1 的父级 是否为 logger2
        System.out.println(logger1.getParent() == logger2);
        // 所有日志记录器的顶级父元素 LogManager$RootLogger，name ""
        System.out.println("logger2 Parent: " + logger2.getParent() + " , name: " + logger2.getParent().getName());

        // 2. 关闭父Logger 默认配置
        logger2.setUseParentHandlers(false);

        // 3. 配置父 Logger 日志级别
        ConsoleHandler consoleHandler = new ConsoleHandler();
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);

        // 3.6 配置日志具体级别
        logger2.setLevel(Level.ALL); // 设置全局日志级别
        consoleHandler.setLevel(Level.FINE); // 设置具体的 Handler 级别
        // 注意，最终的日志级别，以设置中最高的级别为准

        // 4. 使用不同日志级别的方法，输出日志记录
        logger1.severe("severe");
        logger1.warning("warning");
        logger1.info("info");
        logger1.config("config");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");
    }

    // 测试加载自定义配置文件
    @Test
    public void testLogProperties() throws IOException {
        // 读取配置文件，通过类加载器
        InputStream ins = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");
        // 创建 LogManager
        LogManager logManager = LogManager.getLogManager();
        // 通过 LogManager 加载配置文件
        logManager.readConfiguration(ins);

        // 1. 创建日志记录器对象
        Logger logger = Logger.getLogger("com.moon.log");

        // 2. 使用不同日志级别的方法，输出日志记录
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

}
