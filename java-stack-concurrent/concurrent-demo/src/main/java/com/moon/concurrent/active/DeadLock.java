package com.moon.concurrent.active;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 死锁示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-16 22:54
 * @description
 */
public class DeadLock {

    private final static Logger LOGGER = LoggerFactory.getLogger(DeadLock.class);

    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                LOGGER.debug("t1 get lock A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    LOGGER.debug("t1 get lock B");
                    LOGGER.debug("t1 operate....");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                LOGGER.debug("t2 get lock B");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    LOGGER.debug("t2 get lock A");
                    LOGGER.debug("t2 operate...");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
