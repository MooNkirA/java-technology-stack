package com.moon.concurrent.pool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用 java.util.Timer 实现线程任务调度与延迟
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-01 23:02
 * @description
 */
@Slf4j
public class TimerForScheduleTest {

    // 使用 timer 添加两个任务，希望它们都在 1s 后执行
    @Test
    public void timerTest() throws InterruptedException {
        Timer timer = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 2");
            }
        };

        log.debug("start...");
        // 但由于 timer 内只有一个线程来顺序执行队列中的任务，因此『任务1』的延时，影响了『任务2』的执行
        timer.schedule(task1, 1000);
        timer.schedule(task2, 1000);
        Thread.sleep(5000);
    }

    // 使用 ScheduledExecutorService 改写
    @Test
    public void newScheduledThreadPoolTest() throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        log.debug("start...");
        pool.schedule(() -> {
            log.debug("task1");
        }, 1, TimeUnit.SECONDS);

        pool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
}
