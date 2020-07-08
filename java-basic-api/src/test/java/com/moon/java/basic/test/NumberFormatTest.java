package com.moon.java.basic.test;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * NumberFormat 基础API测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-7 14:29
 * @description
 */
public class NumberFormatTest {

    @Test
    public void numberFormatBasicTest() {
        double num = 1102834.83343434;
        // 指定地区
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 将数值格式化后转成字符串类型
        String numberString = format.format(num);

        System.out.println("Locale.CHINA：" + numberString); // 输出：Locale.CHINA：￥1,102,834.83
        StringBuilder sb = new StringBuilder(numberString);
        sb.deleteCharAt(0);
        System.out.println(num); // 输出：1102834.83343434
        System.out.println(sb.toString()); // 输出：1,102,834.83
    }

}
