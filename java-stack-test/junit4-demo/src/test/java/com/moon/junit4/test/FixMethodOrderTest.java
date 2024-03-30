package com.moon.junit4.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 自定义测试方法的顺序示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 18:21
 * @description
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FixMethodOrderTest {

    @Test
    public void testA() {
        System.out.println("first");
    }

    @Test
    public void testC() {
        System.out.println("third");
    }

    @Test
    public void testB() {
        System.out.println("second");
    }

}
