package com.moon.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可重入测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-26 14:14
 * @description
 */
public class ReentrantLockReentrant {

    private final static ReentrantLock reentrantLock = new ReentrantLock();

    @Test
    public void testReentrant() {
        method1();
    }

    public void method1() {
        // 第1次获取锁
        reentrantLock.lock();
        try {
            System.out.println("execute method1");
            method2();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void method2() {
        // 第2次获取锁，可多次获取
        reentrantLock.lock();
        try {
            System.out.println("execute method2");
            method3();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void method3() {
        reentrantLock.lock();
        try {
            System.out.println("execute method3");
        } finally {
            reentrantLock.unlock();
        }
    }

}
