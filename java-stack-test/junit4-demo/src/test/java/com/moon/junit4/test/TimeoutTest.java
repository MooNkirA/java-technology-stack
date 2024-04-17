package com.moon.junit4.test;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 时间测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 17:43
 * @description
 */
public class TimeoutTest {

    @Test(timeout = 1000)
    public void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5000);
        System.out.println("in timeout exception");
    }

}
