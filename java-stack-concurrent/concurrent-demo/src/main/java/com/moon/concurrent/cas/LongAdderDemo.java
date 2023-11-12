package com.moon.concurrent.cas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * LongAdder 示例，并与 AtomicLong 进行性能比较
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-22 23:11
 * @description
 */
public class LongAdderDemo {

    @Test
    public void atomicLongTest() {
        // 测试 AtomicLong
        increment(AtomicLong::new, AtomicLong::incrementAndGet);
        // 测试 LongAdder
        increment(LongAdder::new, LongAdder::increment);
    }

    private <T> void increment(Supplier<T> supplier, Consumer<T> action) {
        // 获取测试的原子类
        T oprator = supplier.get();
        List<Thread> ts = new ArrayList<>();
        long start = System.nanoTime();

        // 40 个线程，每个累加 50 万
        for (int i = 0; i < 40; i++) {
            // 每个线程对数组作 10000 次操作
            ts.add(new Thread(() -> {
                for (int j = 0; j < 500000; j++) {
                    action.accept(oprator); // 操作
                }
            }));
        }

        ts.forEach(Thread::start); // 启动所有线程
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); // 等所有线程结束
        long end = System.nanoTime();
        System.out.println(oprator + " cost:" + (end - start) / 1000_000);
    }

}
