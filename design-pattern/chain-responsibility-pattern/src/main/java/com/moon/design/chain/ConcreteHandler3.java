package com.moon.design.chain;

/**
 * 具体处理者角色3
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 14:21
 * @description
 */
public class ConcreteHandler3 extends Handler {

    @Override
    public boolean doHandle(String request) {
        System.out.println("流转至 ConcreteHandler3");
        boolean b = request.equals("three");
        if (b) {
            System.out.println("具体处理者3负责处理该请求！");
        }
        return b;
    }

}
