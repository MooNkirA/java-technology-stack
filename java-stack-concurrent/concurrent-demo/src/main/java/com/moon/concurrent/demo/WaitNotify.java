package com.moon.concurrent.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * wait/notify 等待与唤醒测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-15 16:42
 * @description
 */
public class WaitNotify {

    private final static Logger LOGGER = LoggerFactory.getLogger(WaitNotify.class);

    private final static Object lock = new Object();

    /**
     * wait 方法调用让线程等待
     */
    @Test
    public void testWait() {
        LOGGER.info("程序开始...");

        synchronized (lock) {
            /*
             * wait 方法必须要由锁对象调用，并且需要在同步代码中调用
             *  示例当前只有主线程，没有其他线程对其唤醒，所以会一直在等待
             */
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("程序结束...");
    }

    /**
     * wait 方法调用，指定让线程等待的最大时间
     */
    @Test
    public void testWaitTimeout() {
        LOGGER.info("程序开始...");

        synchronized (lock) {
            /*
             * wait 方法必须要由锁对象调用，并且需要在同步代码中调用
             *  示例当前指定了最大等待时间，到时间后线程会自动被唤醒
             *  如果时间设置为0，代表无限等待
             */
            try {
                lock.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 3秒后输出
        LOGGER.info("程序结束...");
    }

    /**
     * wait 方法调用让线程等待，notify 方法对线程唤醒
     */
    @Test
    public void testWaitNotify() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                LOGGER.debug("t1 线程执行....");
                try {
                    lock.wait(); // 让线程在 lock 锁对象上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.debug("t1 线程结束....");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                LOGGER.debug("t2 线程执行....");
                try {
                    lock.wait(); // 让线程在 lock 锁对象上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.debug("t2 线程结束....");
            }
        }, "t2");

        t1.start();
        t2.start();

        // 主线程休眠3秒
        Thread.sleep(3000);
        LOGGER.debug("主线程唤醒 lock 对象锁上 wait 的线程");
        synchronized (lock) {
            lock.notify(); // 随机唤醒 lock 对象锁一个等待的线程
        }
        // 因为这里只会唤醒一个线程，别一个线程没有唤醒，所以会一直等待。
        // 然后主线程因为调用了两个线程的 join 方法，也会在这里阻塞等待线程的执行结束
        t1.join();
        t2.join();
    }

    /**
     * wait 方法调用让线程等待，notifyAll 方法对所有线程唤醒
     */
    @Test
    public void testWaitNotifyAll() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                LOGGER.debug("t1 线程执行....");
                try {
                    lock.wait(); // 让线程在 lock 锁对象上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.debug("t1 线程结束....");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                LOGGER.debug("t2 线程执行....");
                try {
                    lock.wait(); // 让线程在 lock 锁对象上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.debug("t2 线程结束....");
            }
        }, "t2");

        t1.start();
        t2.start();

        // 主线程休眠3秒
        Thread.sleep(3000);
        LOGGER.debug("主线程唤醒 lock 对象锁上所有 wait 的线程");
        synchronized (lock) {
            lock.notifyAll(); // 唤醒 lock 对象锁所有等待的线程
        }
        // 因为 notifyAll 方法唤醒所有等待的线程，所以此处两个线程可以执行结束
        t1.join();
        t2.join();
    }

}
