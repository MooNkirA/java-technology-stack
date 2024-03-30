package com.moon.junit4.test;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 禁用测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 16:55
 * @description
 */
public class IgnoreTest {

    @Ignore
    @Test
    public void testIgnore() {
        System.out.println("ignore test");
    }

}
