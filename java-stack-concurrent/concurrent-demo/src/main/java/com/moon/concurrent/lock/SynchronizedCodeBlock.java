package com.moon.concurrent.lock;

/**
 * synchronized 修饰代码块实现线程安全
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-15 8:36
 * @description
 */
public class SynchronizedCodeBlock {

    private static int count = 0;
    private final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                // synchronized 修饰代码，让代码块内的代码同时只有一个线程能执行
                synchronized (lock) {
                    count--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // 程序如果是线程安全的，最终结果是0
        System.out.println("count: " + count);
    }

}
