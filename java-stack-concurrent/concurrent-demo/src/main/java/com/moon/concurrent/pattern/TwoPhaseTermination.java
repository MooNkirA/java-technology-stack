package com.moon.concurrent.pattern;

/**
 * 终止模式之两阶段终止模式示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-14 15:32
 * @description
 */
public class TwoPhaseTermination {

    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始....");
        // 方案1：利用 isInterrupted 打断标识
        // useIsInterrupted();
        // 方案2：自定义停止标记
        useCustomFlag();
        System.out.println("程序结束....");
    }

    // 方案1：利用 isInterrupted 打断标识
    public static void useIsInterrupted() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    System.out.println("收到结束指示，进行结束前处理！");
                    break;
                }
                try {
                    // 注意：当sleep状态被打断后，打断标识会被清除，所以异常捕获后要手动再次进行打断，因为运行时打断不会清除打断标识
                    Thread.sleep(1000);
                    System.out.println("线程的业务处理....");
                } catch (InterruptedException e) {
                    current.interrupt();
                }

            }
        }, "监控线程");
        t.start();

        Thread.sleep(3500);
        // 打断线程
        t.interrupt();
    }

    // 方案2：自定义停止标记
    public static void useCustomFlag() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                if (stop) {
                    System.out.println("收到结束指示，进行结束前处理！");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("线程的业务处理....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "监控线程");
        t.start();

        Thread.sleep(3500);
        // 打断线程并设置标识为true
        t.interrupt();
        stop = true;
    }

}
