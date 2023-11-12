package com.moon.concurrent.cas;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-22 23:02
 * @description
 */
public class AtomicReferenceDemo {

    AtomicReference<BigDecimal> balance;

    @Test
    public void atomicReferenceTest() {
        balance = new AtomicReference<>(new BigDecimal(10000));
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                while (true) {
                    BigDecimal prev = balance.get();
                    BigDecimal next = prev.subtract(new BigDecimal(10));
                    if (balance.compareAndSet(prev, next)) {
                        break;
                    }
                }
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(balance.get());
    }

}
