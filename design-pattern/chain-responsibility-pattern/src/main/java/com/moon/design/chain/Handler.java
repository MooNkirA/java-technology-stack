package com.moon.design.chain;

/**
 * 抽象处理者角色
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 14:20
 * @description
 */
public abstract class Handler {

    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    // 使用模板模式，定义具体处理请求的流程的方法
    public final void handleRequest(String request) {
        // 调用子类实现的处理流程
        if (!doHandle(request)) {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }

    // 定义抽象的模板方法，由每个子类具体实现
    public abstract boolean doHandle(String request);
}
