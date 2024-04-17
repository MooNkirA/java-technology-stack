package com.moon.jvm;

import java.util.ArrayList;

/**
 * 用于测试 GC 日志输出。
 * 测试参数：  -Xms60m -Xmx60m -XX:SurvivorRatio=8 -XX:+PrintGCDetails -Xloggc:./gc.log
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-12 12:05
 * @description
 */
public class GCLogTest {

    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            byte[] arr = new byte[1024 * 100];//100KB
            list.add(arr);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
