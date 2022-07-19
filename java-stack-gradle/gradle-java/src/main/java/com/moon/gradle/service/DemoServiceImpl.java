package com.moon.gradle.service;

import org.springframework.stereotype.Service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-22 16:44
 * @description
 */
@Service
public class DemoServiceImpl implements DemoService{
    @Override
    public void doSomething() {
        System.out.println("调用成功");
    }
}
