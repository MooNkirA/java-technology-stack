package com.moon.java.util.concurrent;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService 内置线程池使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-12-25 18:20
 * @description
 */
public class ExecutorServiceDemo {

    @Test
    public void shutdownTest() {
        // 使用工厂类获取线程池对象
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new ExecutorServiceDemoRunnable(i));
        }
        // 关闭线程池,仅仅是不再接受新的任务,以前的任务还会继续执行
        es.shutdown();
        es.submit(new ExecutorServiceDemoRunnable(888)); // 不能再提交新的任务了
    }

    @Test
    public void shutdownNowTest() {
        // 使用工厂类获取线程池对象
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 提交任务
        for (int i = 1; i <= 10; i++) {
            es.submit(new ExecutorServiceDemoRunnable(i));
        }
        // 立刻关闭线程池，如果线程池中还有缓存的任务，没有执行，则取消执行，并返回这些任务
        List<Runnable> runnables = es.shutdownNow();
        System.out.println(runnables);
    }
}

/**
 * 任务类,包含一个任务编号,在任务中,打印出是哪一个线程正在执行任务
 */
class ExecutorServiceDemoRunnable implements Runnable {
    private final int id;

    public ExecutorServiceDemoRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // 获取线程的名称并输出
        String name = Thread.currentThread().getName();
        System.out.println(name + "执行了任务..." + id);
    }

    @Override
    public String toString() {
        return "ExecutorServiceDemoRunnable{id=" + id + "}";
    }
}