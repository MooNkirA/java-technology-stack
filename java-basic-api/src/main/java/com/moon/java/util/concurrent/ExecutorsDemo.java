package com.moon.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 通过 Executors 工具类获取不同类型的类型线程池示例（ExecutorService、ScheduledExecutorService 等）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-12-25 18:06
 * @description
 */
public class ExecutorsDemo {

    // newCachedThreadPool 方法获取线程池测试
    @Test
    public void newCachedThreadPoolTest1() {
        // 1.使用工厂类获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool();
        // 2.提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

    // newCachedThreadPool 方法获取线程池测试
    @Test
    public void newCachedThreadPoolTest2() {
        // 1.使用工厂类获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool(new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义的线程名称" + n++);
            }
        });
        // 2.提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

    // newFixedThreadPool 方法获取线程池测试
    @Test
    public void newFixedThreadPoolTest1() {
        // 1.使用工厂类获取线程池对象
        ExecutorService es = Executors.newFixedThreadPool(3);
        // 2.提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

    // newFixedThreadPool 方法获取线程池测试
    @Test
    public void newFixedThreadPoolTest2() {
        // 1.使用工厂类获取线程池对象
        ExecutorService es = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义的线程名称" + n++);
            }
        });
        // 2.提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

    // newSingleThreadExecutor 方法获取线程池测试
    @Test
    public void newSingleThreadExecutorTest1() {
        // 1.使用工厂类获取线程池对象
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 2.提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

    // newSingleThreadExecutor 方法获取线程池测试
    @Test
    public void newSingleThreadExecutorTest2() {
        // 1.使用工厂类获取线程池对象
        ExecutorService es = Executors.newSingleThreadExecutor(new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义的线程名称" + n++);
            }
        });
        // 2.提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new MyRunnable(i));
        }
    }

    // newScheduledThreadPool 方法获取定时任务线程池，schedule 方法功能测试
    @Test
    public void newScheduledThreadPoolScheduleTest() throws InterruptedException {
        // 1.获取一个具备延迟执行任务的线程池对象
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
        // 2.创建多个任务对象,提交任务,每个任务延迟2秒执行
        for (int i = 1; i <= 10; i++) {
            es.schedule(new MyRunnable(i), 2, TimeUnit.SECONDS);
        }
        System.out.println("main thread is over");
        Thread.sleep(4000);
    }

    // newScheduledThreadPool 方法获取定时任务线程池，scheduleAtFixedRate 方法功能测试
    @Test
    public void newScheduledThreadPoolScheduleAtFixedRateTest() throws InterruptedException {
        // 1.获取一个具备延迟执行任务的线程池对象
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名:" + n++);
            }
        });
        // 2.创建多个任务对象，延迟1秒开始，每间隔2秒执行一个任务(不包含任务实际运行的时间，即任务完成后再算间隔)
        for (int i = 1; i <= 10; i++) {
            es.scheduleAtFixedRate(new MyRunnable2(i), 1, 2, TimeUnit.SECONDS);
        }
        System.out.println("main thread is over");
        Thread.sleep(20000);
    }

    // newScheduledThreadPool 方法获取定时任务线程池，schedule 方法功能测试
    @Test
    public void newSingleThreadScheduledExecutorScheduleWithFixedDelayTest() throws InterruptedException {
        // 1.获取一个具备延迟执行任务的线程池对象
        // 注：这里想更容易看出效果，使用只有一个线程的定时任务线程池
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名:" + n++);
            }
        });
        // 2.创建多个任务对象，延迟1秒开始，每间隔2秒执行一个任务(包含任务实际运行的时间)
        for (int i = 1; i <= 10; i++) {
            es.scheduleWithFixedDelay(new MyRunnable2(i), 1, 2, TimeUnit.SECONDS);
        }
        System.out.println("main thread is over");
        Thread.sleep(20000);
    }


}

// 用于测试的任务类，包含一个任务编号属性。在任务中，打印出是哪一个线程正在执行任务
class MyRunnable implements Runnable {
    private final int id;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // 获取线程的名称输出
        String name = Thread.currentThread().getName();
        System.out.println(name + "执行了任务..." + id);
    }
}

class MyRunnable2 implements Runnable {
    private final int id;

    public MyRunnable2(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "执行了任务:" + id);
    }
}