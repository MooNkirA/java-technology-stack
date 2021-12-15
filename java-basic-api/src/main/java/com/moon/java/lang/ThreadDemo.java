package com.moon.java.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;


/**
 * Thread 类基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-13 16:43
 * @description
 */
public class ThreadDemo {

    private final static Logger logger = LoggerFactory.getLogger(ThreadDemo.class);

    /**
     * Thread 创建的线程
     */
    @Test
    public void testCrateThread() throws InterruptedException {
        // 直接创建一个线程
        Thread t1 = new Thread() {
            @Override
            public void run() {
                logger.info("默认名称 {} 线程执行了....", Thread.currentThread().getName());
            }
        };
        // 创建带名称的线程
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                logger.info("自定义名称 t2 线程执行了....");
            }
        };

        // 开启全部线程
        t1.start();
        t2.start();

        // 输出线程的名称
        logger.info("默认名称线程是：" + t1.getName());
        logger.info("自定义名称线程是：" + t2.getName());
    }

    /**
     * Thread 构造方法传入 Runnable 创建的线程
     */
    @Test
    public void testCrateThreadByRunnable() throws InterruptedException {
        // 通过 lambda 表达式创建 Runnable 实现
        Runnable r1 = () -> logger.info("默认名称 {} 线程执行了....", Thread.currentThread().getName());
        Runnable r2 = () -> logger.info("自定义名称 t2 线程执行了....");

        // 直接创建一个线程
        Thread t1 = new Thread(r1);
        // 创建带名称的线程
        Thread t2 = new Thread(r2, "t2");

        // 开启全部线程
        t1.start();
        t2.start();

        // 输出线程的名称
        logger.info("默认名称线程是：" + t1.getName());
        logger.info("自定义名称线程是：" + t2.getName());
    }

    /**
     * Thread 修改线程名称与获取线程名称
     */
    @Test
    public void testSetAndGetName() throws InterruptedException {
        // 直接创建一个默认名称的线程
        Thread t1 = new Thread(() -> {
            // getName 方法，获取线程名称
            logger.info("名称为 {} 的线程执行...", Thread.currentThread().getName());
        });

        // 修改线程名称
        t1.setName("MooN");
        // 开启线程
        t1.start();
        // 主线程等待子线程执行完
        t1.join();
    }

    /**
     * 获取当前线程的状态
     */
    @Test
    public void testGetState() throws InterruptedException {
        // 直接创建一个默认名称的线程
        Thread t1 = new Thread(() -> {
            // getName 方法，获取线程名称
            logger.info("名称为 {} 的线程执行...", Thread.currentThread().getName());
            try {
                Thread.sleep(2000); // 休眠2秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        // getState 方法获取当前线程的状态
        logger.info("t1 线程当前状态：{}", t1.getState()); // NEW
        // 开启线程
        t1.start();
        logger.info("t1 线程当前状态：{}", t1.getState()); // RUNNABLE

        try {
            // 主线程休眠0.5秒
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("t1 线程当前状态：{}", t1.getState()); // TIMED_WAITING

        // 主线程等待子线程执行完
        t1.join();
    }

    /**
     * 线程休眠 sleep
     */
    @Test
    public void testSleep() throws InterruptedException {
        // 直接创建一个默认名称的线程
        Thread t1 = new Thread(() -> {
            long start = System.currentTimeMillis();
            logger.info("线程执行开始...");
            try {
                // 调用 sleep 会让当前线程休眠，会从 Running 状态进入 Timed Waiting 状态（阻塞）
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("线程执行结束，耗时：{} ms", System.currentTimeMillis() - start);
        });

        // 开启线程
        t1.start();

        // 主线程等待子线程执行完
        t1.join();
    }

    /**
     * 线程休眠 使用 TimeUnit 代替 sleep 方法
     */
    @Test
    public void testTimeUnit() throws InterruptedException {
        // 直接创建一个默认名称的线程
        Thread t1 = new Thread(() -> {
            long start = System.currentTimeMillis();
            logger.info("线程执行开始...");
            try {
                // TimeUnit 枚举类可以指定当前时间的单位，调用其 sleep 方法的效果与 Thread 类的 sleep 方法一样。
                // 但 TimeUnit 的可读性更好
                TimeUnit.SECONDS.sleep(2); // 休眠2秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("线程执行结束，耗时：{} ms", System.currentTimeMillis() - start);
        });

        // 开启线程
        t1.start();

        // 主线程等待子线程执行完
        t1.join();
    }

    /**
     * yield 让出当前线程对CPU的使用，并执行其他线程
     */
    @Test
    public void testYield() {
        Runnable task1 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println("---->1 " + count++);
            }
        };
        Runnable task2 = () -> {
            int count = 0;
            for (; ; ) {
                // 调用 yield 方法让出此线程的CPU使用权，增加其他线程的被cpu调度的机率
                Thread.yield();
                System.out.println("              ---->2 " + count++);
            }
        };

        Thread t1 = new Thread(task1, "t1"); // 测试输出结果：count 64
        Thread t2 = new Thread(task2, "t2"); // 测试输出结果：count 2

        t1.start();
        t2.start();
    }

    /**
     * 设置线程的优先级，只是提示CPU，增大执行到的概率，但最终还是由调度器决定
     */
    @Test
    public void testSetPriority() {
        Runnable task1 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println("---->1 " + count++);
            }
        };
        Runnable task2 = () -> {
            int count = 0;
            for (; ; ) {
                System.out.println("              ---->2 " + count++);
            }
        };

        Thread t1 = new Thread(task1, "t1"); // 测试输出结果：count 69
        Thread t2 = new Thread(task2, "t2"); // 测试输出结果：count 87

        /* 设置线程优先级 */
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }

    /**
     * join 方法，设置等待线程运行结束
     */
    @Test
    public void testJoin() throws InterruptedException {
        logger.info("主线程开始....");
        AtomicInteger count = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            logger.info("t1 线程开始....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("t1 线程结束....");
            count.set(10);
        }, "t1");
        t1.start();
        // 在主线程调用 t1 线程的 join 方法，主线程会阻塞等待 t1 线程结束
        t1.join();

        logger.info("结果为:{}", count.get());
        logger.info("主线程结束....");
    }

    /**
     * 多个线程调用 join 方法，设置等待线程运行结束
     */
    @Test
    public void testMultiJoin() throws InterruptedException {
        long start = System.currentTimeMillis();
        logger.info("主线程开始....");
        AtomicInteger count1 = new AtomicInteger();
        AtomicInteger count2 = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            logger.info("t1 线程开始....");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("t1 线程结束....");
            count1.set(10);
        }, "t1");

        Thread t2 = new Thread(() -> {
            logger.info("t2 线程开始....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("t2 线程结束....");
            count2.set(20);
        }, "t2");

        t1.start();
        t2.start();
        // 在主线程调用 t1 线程的 join 方法，主线程会阻塞等待 t1 线程结束
        logger.info("线程的 join 方法开始");
        t1.join();
        logger.info("t1 线程 join 结束");
        t2.join();
        logger.info("t2 线程 join 结束");

        logger.info("主线程结束....");
        long end = System.currentTimeMillis();
        logger.info("count1: {}, count2: {}, 耗时: {} ms", count1.get(), count2.get(), end - start);
    }

    /**
     * join 方法，设置最大等待的时长，线程运行结束
     */
    @Test
    public void testJoinTimeUp() throws InterruptedException {
        logger.info("主线程开始....");
        AtomicInteger count = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            logger.info("t1 线程开始....");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("t1 线程结束....");
            count.set(10);
        }, "t1");
        t1.start();
        /*
         * 在主线程调用 t1 线程的 join 方法并设置最大等待时长，主线程会阻塞等待 t1 线程。
         *   如果子线程执行超出此最大等待时间，则主线程不再等待，直接结束。
         *   如果子线程执行小于此最大等待时间，则主线程等待子线程执行完成。
         */
        t1.join(1000);

        logger.info("结果为:{}", count.get());
        logger.info("主线程结束....");
    }

    /**
     * 调用 interrupt 方法，打断 sleep 中的线程，会清空线程的打断标识
     */
    @Test
    public void testInterruptSleep() throws InterruptedException {
        logger.info("主线程开始....");

        Thread t1 = new Thread(() -> {
            logger.info("t1 线程开始休眠2秒....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.info("t1 线程被打断....");
                e.printStackTrace();
            }
            logger.info("t1 线程结束....");
        }, "t1");
        t1.start();
        Thread.sleep(1000);

        // 调用 interrupt 方法，打断休眠中的线程
        logger.info("t1 interrupt");
        t1.interrupt();
        Thread.sleep(200);
        // 线程的打断标识会被清空
        logger.info("t1 线程打断标识：{}", t1.isInterrupted()); // 输出结果：false
        logger.info("主线程结束....");
    }

    /**
     * 调用 interrupt 方法，打断正常运行的线程，不会清空线程的打断标识
     */
    @Test
    public void testInterruptNormal() throws InterruptedException {
        logger.info("主线程开始....");

        Thread t1 = new Thread(() -> {
            logger.info("t1 线程开始....");
            while (true) {
                // 获取当前线程的打断标识
                boolean interrupted = Thread.currentThread().isInterrupted();
                logger.info("t1 线程打断标识：{}", interrupted); // 打断后输出结果：true
                if (interrupted) {
                    // 线程的打断标识不会被清空
                    logger.info("t1 线程被中断了，退出循环....");
                    break;
                }
            }
            logger.info("t1 线程结束....");
        }, "t1");
        t1.start();
        Thread.sleep(50);

        // 调用 interrupt 方法，打断正常运行的线程
        logger.info("t1 interrupt");
        t1.interrupt();
        logger.info("主线程结束....");
    }

    /**
     * 调用 interrupt 方法，打断 park 线程，不会清空线程的打断标识
     */
    @Test
    public void testInterruptPark() throws InterruptedException {
        logger.info("主线程开始....");

        Thread t1 = new Thread(() -> {
            logger.info("LockSupport.park start....");
            LockSupport.park();
            logger.info("LockSupport.park end....");
            logger.info("t1 线程打断标识：{}", Thread.currentThread().isInterrupted()); // 输出结果：true
        }, "t1");
        t1.start();
        Thread.sleep(1000);

        // 调用 interrupt 方法，打断 park 的线程
        logger.info("t1 interrupt");
        t1.interrupt();
        logger.info("主线程结束....");
    }

    /**
     * 设置守护线程
     */
    @Test
    public void testSetDaemon() throws InterruptedException {
        logger.info("主线程开始....");

        Thread t1 = new Thread(() -> {
            logger.info("t1 线程开始....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("t1 线程结束....");
        }, "t1");

        // 设置该线程为守护线程，当主线程结束时，假如该线程的代码还没有执行完，也会强制结束
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(1000);
        logger.info("主线程结束....");
    }


}
