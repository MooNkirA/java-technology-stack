package com.moon.junit4.test.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 套件测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 18:13
 * @description
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JunitTest1.class, JunitTest2.class}) // 此处类的配置顺序会影响执行顺序
public class JunitSuiteTest {
}
