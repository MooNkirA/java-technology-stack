package com.moon.concurrent.lock;

/**
 * 优化 synchronized 使用面向对象的方式去保证线程安全
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-15 8:36
 * @description
 */
public class SynchronizedClass {

    public static void main(String[] args) throws InterruptedException {
        // 创建包含共享资源的对象，在对象中进行线程安全的控制
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // 程序如果是线程安全的，最终结果是0
        System.out.println("count: " + counter.get());
    }

}

class Counter {
    private int count = 0;

    public void increment() {
        // synchronized 修饰代码，让代码块内的代码同时只有一个线程能执行
        synchronized (this) {
            count++;
        }
    }

    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int get() {
        synchronized (this) {
            return count;
        }
    }
}
