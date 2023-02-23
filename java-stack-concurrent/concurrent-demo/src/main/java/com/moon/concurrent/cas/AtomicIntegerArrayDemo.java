package com.moon.concurrent.cas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArray 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-22 23:09
 * @description
 */
public class AtomicIntegerArrayDemo {

    /**
     * 数组不安全的操作示例
     */
    @Test
    public void unsafeArrayTest() {
        List<Thread> ts = new ArrayList<>();
        int[] array = new int[10];
        int length = array.length;

        for (int i = 0; i < length; i++) {
            // 每个线程对数组作 10000 次操作
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    array[j % length]++;
                }
            }));
        }

        ts.forEach(Thread::start); // 启动所有线程
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); // 等所有线程结束
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void AtomicIntegerArrayTest() {
        List<Thread> ts = new ArrayList<>();
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        int length = array.length();

        for (int i = 0; i < length; i++) {
            // 每个线程对数组作 10000 次操作
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    array.getAndIncrement(j % length);
                }
            }));
        }

        ts.forEach(Thread::start); // 启动所有线程
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); // 等所有线程结束
        System.out.println(array);
    }

}
