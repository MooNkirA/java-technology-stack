package com.moon.design.chain;

/**
 * 具体处理者角色1
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 14:21
 * @description
 */
public class ConcreteHandler1 extends Handler {

    @Override
    public boolean doHandle(String request) {
        System.out.println("流转至 ConcreteHandler1");
        boolean b = request.equals("one");
        if (b) {
            System.out.println("具体处理者1负责处理该请求！");
        }
        return b;
    }

}
