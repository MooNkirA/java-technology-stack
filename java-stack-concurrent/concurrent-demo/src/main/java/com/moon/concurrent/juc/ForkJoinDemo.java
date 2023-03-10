package com.moon.concurrent.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork / Join 基础使用示例
 * <p>
 * 定义了一个对 1~n 之间的整数求和的任务
 * </p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-03 22:08
 * @description
 */
@Slf4j
public class ForkJoinDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        // log.info("ForkJoinTask1 任务计算结果：{}", pool.invoke(new ForkJoinTask1(5)));
        // log.info("ForkJoinTask1 任务计算结果：{}", pool.invoke(new ForkJoinTask2(1, 10)));
        log.info("ForkJoinTask1 任务计算结果：{}", pool.invoke(new ForkJoinTask3(1, 5)));
    }

}

@Slf4j
class ForkJoinTask1 extends RecursiveTask<Integer> {
    int n;

    public ForkJoinTask1(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + '}';
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            log.debug("join() {}", n);
            return n;
        }
        ForkJoinTask1 t1 = new ForkJoinTask1(n - 1);

        t1.fork();
        log.debug("fork() {} + {}", n, t1);
        int result = n + t1.join();
        log.debug("join() {} + {} = {}", n, t1, result);
        return result;
    }
}

@Slf4j
class ForkJoinTask2 extends RecursiveTask<Integer> {

    int begin;
    int end;

    public ForkJoinTask2(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }

    @Override
    protected Integer compute() {
        if (begin == end) {
            log.debug("join() {}", begin);
            return begin;
        }
        if (end - begin == 1) {
            log.debug("join() {} + {} = {}", begin, end, end + begin);
            return end + begin;
        }
        int mid = (end + begin) / 2;

        ForkJoinTask2 t1 = new ForkJoinTask2(begin, mid - 1);
        t1.fork();
        ForkJoinTask2 t2 = new ForkJoinTask2(mid + 1, end);
        t2.fork();
        log.debug("fork() {} + {} + {} = ?", mid, t1, t2);

        int result = mid + t1.join() + t2.join();
        log.debug("join() {} + {} + {} = {}", mid, t1, t2, result);
        return result;
    }
}

@Slf4j
class ForkJoinTask3 extends RecursiveTask<Integer> {

    int begin;
    int end;

    public ForkJoinTask3(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }

    @Override
    protected Integer compute() {
        if (begin == end) {
            log.debug("join() {}", begin);
            return begin;
        }
        if (end - begin == 1) {
            log.debug("join() {} + {} = {}", begin, end, end + begin);
            return end + begin;
        }
        int mid = (end + begin) / 2;

        ForkJoinTask3 t1 = new ForkJoinTask3(begin, mid);
        t1.fork();
        ForkJoinTask3 t2 = new ForkJoinTask3(mid + 1, end);
        t2.fork();
        log.debug("fork() {} + {} = ?", t1, t2);

        int result = t1.join() + t2.join();
        log.debug("join() {} + {} = {}", t1, t2, result);
        return result;
    }
}