package com.moon.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于使用 Arthas 测试查看程序运行情况
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-12 10:28
 * @description
 */
public class GCTest {

    public static void main(String[] args) throws InterruptedException {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < 100_0000; i++) {
            l.add(new String("dddddd"));
            Thread.sleep(100);
        }
    }

}
