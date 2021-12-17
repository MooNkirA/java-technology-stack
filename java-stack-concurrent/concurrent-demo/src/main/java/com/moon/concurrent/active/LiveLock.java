package com.moon.concurrent.active;

/**
 * 活锁示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-16 22:57
 * @description
 */
public class LiveLock {

    private static volatile int count = 10;

    public static void main(String[] args) {
        new Thread(() -> {
            // 期望减到 0 退出循环
            while (count > 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println("count: " + count);
            }
        }, "t1").start();
        new Thread(() -> {
            // 期望超过 20 退出循环
            while (count < 20) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println("count: " + count);
            }
        }, "t2").start();
    }
}
