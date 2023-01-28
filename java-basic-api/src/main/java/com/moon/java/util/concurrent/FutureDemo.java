package com.moon.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步计算结果(Future) 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-01-01 22:06
 * @description
 */
public class FutureDemo {

    @Test
    public void FutureBasicTest() throws ExecutionException, InterruptedException {
        // 1.获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool();
        // 2.创建 Callable 类型的任务对象，并且启动
        Future<Integer> future = es.submit(new MyCallable(1, 1));
        // 3.判断任务是否已经完成
        boolean done = future.isDone();
        System.out.println("第一次判断任务是否完成:" + done);
        boolean cancelled = future.isCancelled();
        System.out.println("第一次判断任务是否取消:" + cancelled);
        Integer v = future.get(); // 一直等待任务的执行,直到完成为止
        System.out.println("任务执行的结果是:" + v);
        boolean done2 = future.isDone();
        System.out.println("第二次判断任务是否完成:" + done2);
        boolean cancelled2 = future.isCancelled();
        System.out.println("第二次判断任务是否取消:" + cancelled2);
    }

    // 测试 Future 对象的 cancel 方法
    @Test
    public void FutureCancelTest() throws Exception {
        // 1.获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool();
        // 2.创建 Callable 类型的任务对象，并且启动
        Future<Integer> future = es.submit(new MyCallable(1, 1));
        // 3.判断任务是否已经完成
        boolean b = future.cancel(true);
        System.out.println("取消任务执行的结果:" + b);
    }

    // 测试 Future 对象设置等待时间的 get 方法
    @Test
    public void FutureGetTimeoutTest() throws Exception {
        // 1.获取线程池对象
        ExecutorService es = Executors.newCachedThreadPool();
        // 2.创建 Callable 类型的任务对象，并且启动
        Future<Integer> future = es.submit(new MyCallable(1, 1));
        // 3.设置最大等待结果时间
        Integer v = future.get(1, TimeUnit.SECONDS); // 由于等待时间过短,任务来不及执行完成,会报异常
        System.out.println("任务执行的结果是:" + v);
    }

}

class MyCallable implements Callable<Integer> {
    private final int a;
    private final int b;

    public MyCallable(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println(name + "准备开始计算...");
        Thread.sleep(2000);
        System.out.println(name + "计算完成...");
        return a + b;
    }
}
