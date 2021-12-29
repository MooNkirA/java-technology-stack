package com.moon.concurrent.lock;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可被打断测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-26 14:21
 * @description
 */
public class ReentrantLockInterrupt {

    private final static ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * ReentrantLock 等待获取锁不可被打断
     */
    @Test
    public void test1() throws IOException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1线程启动了...");
            // lock 方法，t1 线程进行等待，获取锁。此方式不会被 interrupt 打断
            reentrantLock.lock();
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
            t1.interrupt();
            System.out.println("主线程执行 interrupt 打断");
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
     * ReentrantLock 等待获取锁可被打断
     */
    @Test
    public void test2() throws IOException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1线程启动了..");
            try {
                // lockInterruptibly 方法获取锁，等待过程是可以被打断
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t1线程等待锁的过程中被打断...");
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
        t1.start();
        try {
            Thread.sleep(1000);
            t1.interrupt();
            System.out.println("主线程执行 interrupt 打断");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
            System.out.println("主线程释放锁");
        }
        System.in.read();
    }

}
