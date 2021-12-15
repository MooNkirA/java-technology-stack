package com.moon.concurrent.demo;

/**
 * 并发编程，利用休眠减少 CPU 占用率
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-14 11:16
 * @description
 */
public class LessCpuLoad {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    /*
                     * 在没有利用 cpu 来计算时，让 while(true) 空转会浪费 cpu，
                     * 这时可以使用 yield 或 sleep 来让出 cpu 的使用权给其他程序，
                     * 从而减少cpu占用率
                     */
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
