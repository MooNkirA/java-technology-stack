package com.moon.java.jdk8stream;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * JDK8 新特性 Stream - parallelStream底层实现Fork/Join案例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-28 09:35
 * @description
 */
public class Demo07ForkJoin {

    @Test
    public void forkJoinTest() {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        SumRecursiveTask task = new SumRecursiveTask(1, 99999999999L);
        Long result = pool.invoke(task);
        System.out.println("result = " + result);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间: " + (end - start));
    }

}

/**
 * 创建一个求和的任务
 */
class SumRecursiveTask extends RecursiveTask<Long> {
    // 定义是否要拆分的临界值
    private static final long THRESHOLD = 3000L;
    // 起始值
    private final long start;
    // 结束值
    private final long end;

    // 定义构造器，指定起始与结束值
    public SumRecursiveTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    // 处理计算逻辑
    @Override
    protected Long compute() {
        long length = end - start;
        if (length > THRESHOLD) {
            // 大于临界值，进行拆分
            long middle = (start + end) / 2;
            SumRecursiveTask left = new SumRecursiveTask(start, middle);
            left.fork();
            SumRecursiveTask right = new SumRecursiveTask(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        } else {
            // 小于临界值，执行计算
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
