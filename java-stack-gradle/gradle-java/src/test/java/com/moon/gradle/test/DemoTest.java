package com.moon.gradle.test;

import com.moon.gradle.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-22 16:45
 * @description
 */
public class DemoTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.moon.gradle");
        DemoService bean = context.getBean(DemoService.class);
        bean.doSomething();
    }
}
