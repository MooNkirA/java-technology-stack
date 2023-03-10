package com.moon.java.util.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService 内置线程池使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-12-25 18:20
 * @description
 */
@Slf4j
public class ExecutorServiceDemo {

    // submit 方法示例
    @Test
    public void submitTest() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<String> future = pool.submit(() -> {
            log.debug("running");
            Thread.sleep(1000);
            return "ok";
        });

        log.debug("{}", future.get());
    }

    // invokeAll 方法示例
    @Test
    public void invokeAllTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(2000);
                    return "3";
                }
        ));

        futures.forEach(f -> {
            try {
                log.debug("{}", f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    // invokeAny 方法示例
    @Test
    public void invokeAnyTest() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        String result = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin 1");
                    Thread.sleep(1000);
                    log.debug("end 1");
                    return "1";
                },
                () -> {
                    log.debug("begin 2");
                    Thread.sleep(500);
                    log.debug("end 2");
                    return "2";
                },
                () -> {
                    log.debug("begin 3");
                    Thread.sleep(2000);
                    log.debug("end 3");
                    return "3";
                }
        ));
        log.debug("{}", result);
    }

    // shutdown 方法示例
    @Test
    public void shutdownTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> result1 = pool.submit(() -> {
            log.debug("task 1 running...");
            Thread.sleep(1000);
            log.debug("task 1 finish...");
            return 1;
        });

        Future<Integer> result2 = pool.submit(() -> {
            log.debug("task 2 running...");
            Thread.sleep(1000);
            log.debug("task 2 finish...");
            return 2;
        });

        Future<Integer> result3 = pool.submit(() -> {
            log.debug("task 3 running...");
            Thread.sleep(1000);
            log.debug("task 3 finish...");
            return 3;
        });

        log.debug("shutdown");
        pool.shutdown(); // 关闭后线程还能继续执行
        pool.awaitTermination(3, TimeUnit.SECONDS);
    }

    @Test
    public void shutdownTest2() {
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


    // shutdownNow 方法示例
    @Test
    public void shutdownNowTest() {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> result1 = pool.submit(() -> {
            log.debug("task 1 running...");
            Thread.sleep(1000);
            log.debug("task 1 finish...");
            return 1;
        });

        Future<Integer> result2 = pool.submit(() -> {
            log.debug("task 2 running...");
            Thread.sleep(1000);
            log.debug("task 2 finish...");
            return 2;
        });

        Future<Integer> result3 = pool.submit(() -> {
            log.debug("task 3 running...");
            Thread.sleep(1000);
            log.debug("task 3 finish...");
            return 3;
        });

        log.debug("shutdownNow");
        // 停止所有正在执行的任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
        List<Runnable> runnables = pool.shutdownNow();
        log.debug("other.... {}", runnables);
    }


    @Test
    public void shutdownNowTest2() {
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