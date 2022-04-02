package com.moon.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 基础使用
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-13 9:36
 * @description
 */
public class FutureTaskDemo {

    /**
     * FutureTask 能够接收 Callable 类型的参数，用来处理有返回结果的情况
     */
    @Test
    public void testFutureTaskBasic() throws ExecutionException, InterruptedException {
        // 创建线程任务对象
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("Callable 的 call() 方法开始执行...");
            Thread.sleep(2000);
            // 返回结果
            return "FutureTask 的返回";
        });

        /*
         * 创建并开启线程
         *  参数1: 线程任务对象
         *  参数2: 线程名称
         */
        System.out.println("创建并开启线程...");
        new Thread(futureTask, "th1").start();
        System.out.println("FutureTask#get()方法执行前...");

        // 调用 FutureTask 的 get 方法获取执行完毕的结果。此时主线程会阻塞，等线程执行的结果返回
        String result = futureTask.get();
        System.out.println("FutureTask#get()方法执行后...");
        System.out.println("result: " + result);
    }

}
