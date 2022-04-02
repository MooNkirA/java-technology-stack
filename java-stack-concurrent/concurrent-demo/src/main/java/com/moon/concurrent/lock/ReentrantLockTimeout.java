package com.moon.concurrent.lock;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 等待超时测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-26 22:06
 * @description
 */
public class ReentrantLockTimeout {

    private final static ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * ReentrantLock 尝试获取立即失败
     */
    @Test
    public void test1() throws IOException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1线程启动了...");
            // tryLock 方法立即获取锁对象，成功获取到锁则返回true，否则返回false
            if (!reentrantLock.tryLock()) {
                System.out.println("t1线程获取锁失败，返回...");
                return;
            }
            try {
                System.out.println("t1线程获得了锁...");
            } finally {
                // t1线程释放锁
                reentrantLock.unlock();
            }
        }, "t1");

        reentrantLock.lock();
        System.out.println("主线程获得了锁...");
        t1.start(); // 启动t1线程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 主线程释放锁
            reentrantLock.unlock();
            System.out.println("主线程释放锁");
        }
        System.in.read();
    }

    /**
     * ReentrantLock 设置最大等待时间。
     */
    @Test
    public void test2() throws IOException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1线程启动了...");
            try {
                // tryLock 方法尝试获取，并可以设置最大等待时间。
                if (!reentrantLock.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println("t1线程等1秒后获取锁失败，返回...");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("t1线程获得了锁...");
            } finally {
                // t1线程释放锁
                reentrantLock.unlock();
            }
        }, "t1");

        reentrantLock.lock();
        System.out.println("主线程获得了锁...");
        t1.start(); // 启动t1线程
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 主线程释放锁
            reentrantLock.unlock();
            System.out.println("主线程释放锁");
        }
        System.in.read();
    }

}
