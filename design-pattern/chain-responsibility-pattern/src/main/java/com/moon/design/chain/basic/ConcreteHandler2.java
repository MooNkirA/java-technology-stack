package com.moon.design.chain.basic;

/**
 * 具体处理者角色2
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 14:21
 * @description
 */
public class ConcreteHandler2 extends Handler {

    @Override
    public boolean doHandle(String request) {
        System.out.println("流转至 ConcreteHandler2");
        boolean b = request.equals("two");
        if (b) {
            System.out.println("具体处理者2负责处理该请求！");
        }
        return b;
    }

}
