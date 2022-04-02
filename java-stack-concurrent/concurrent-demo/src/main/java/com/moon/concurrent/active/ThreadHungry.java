package com.moon.concurrent.active;

/**
 * 线程饥饿示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-16 23:12
 * @description
 */
public class ThreadHungry {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(() -> {
                while(true){
                    System.out.println("高优先级线程执行....");
                }
            });
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        }

        Thread.sleep(1000);
        Thread me = new Thread(() ->  System.out.println("......................低优先级线程执行了。") );
        me.setPriority(Thread.MIN_PRIORITY);
        me.start();
    }
}
