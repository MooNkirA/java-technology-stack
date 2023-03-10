package com.moon.concurrent.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发工具类 CountdownLatch 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-07 21:32
 * @description
 */
@Slf4j
public class CountdownLatchDemo {

    @Test
    public void countdownLatchBasicTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        }).start();
        new Thread(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        }).start();
        new Thread(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        }).start();

        log.debug("waiting...");
        latch.await();
        log.debug("wait end...");
    }

    // 可以配合线程池使用
    @Test
    public void countdownLatchWithPoolTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService pool = Executors.newFixedThreadPool(4);

        pool.submit(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        });
        pool.submit(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        });
        pool.submit(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        });

        log.debug("waiting...");
        latch.await();
        log.debug("wait end...");
    }

    // CountdownLatch 应用之同步等待多线程准备完毕
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger num = new AtomicInteger(0);
        ExecutorService threadPool = Executors.newFixedThreadPool(10, (r) -> {
            return new Thread(r, "t" + num.getAndIncrement());
        });

        CountDownLatch latch = new CountDownLatch(10);
        String[] all = new String[10];
        Random r = new Random();

        for (int j = 0; j < 10; j++) {
            int x = j;
            threadPool.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[x] = Thread.currentThread().getName() + "(" + (i + "%") + ")";
                    System.out.print("\r" + Arrays.toString(all));
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("\n游戏开始...");
        threadPool.shutdown();
    }

}
