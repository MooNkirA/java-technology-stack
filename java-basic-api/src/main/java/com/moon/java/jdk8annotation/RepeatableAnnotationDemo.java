package com.moon.java.jdk8annotation;

import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * JDK8 新特性 - @Repeatable定义重复注解的使用
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-29 09:02
 * @description
 */
/* 3. 在类中标识重复注解 */
@MyRepeatableAnnotation("AA")
@MyRepeatableAnnotation("BB")
@MyRepeatableAnnotation("CC")
public class RepeatableAnnotationDemo {

    /* 3. 在方法中标识重复注解 */
    @MyRepeatableAnnotation("XX")
    @MyRepeatableAnnotation("YY")
    public void foo() {
    }

    /*
     * 4. 获取解析重复注解
     *  Class与Method对象的 getAnnotationsByType 方法是新增的API，用于获取重复的注解
     */
    @Test
    public void repeatableAnnotationTest() throws NoSuchMethodException {
        // 获取类上的重复注解
        MyRepeatableAnnotation[] repeatableAnnos = RepeatableAnnotationDemo.class
                .getAnnotationsByType(MyRepeatableAnnotation.class);
        // 循环输出重复注解值
        for (MyRepeatableAnnotation repeatableAnno : repeatableAnnos) {
            System.out.println(repeatableAnno + " 重复注解（标识在类上）的value值为: " + repeatableAnno.value());
        }
        System.out.println("------------------------------");

        // 获取方法上的重复注解
        MyRepeatableAnnotation[] methodRepeatableAnnos = RepeatableAnnotationDemo.class
                .getMethod("foo").getAnnotationsByType(MyRepeatableAnnotation.class);
        for (MyRepeatableAnnotation repeatableAnno : methodRepeatableAnnos) {
            System.out.println(repeatableAnno + " 重复注解（标识在方法上）的value值为: " + repeatableAnno.value());
        }
    }

}

// 1. 定义一个可以重复的注解
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyRepeatableContainer.class)
@interface MyRepeatableAnnotation {
    String value();
}

// 2. 定义重复的注解容器注解
@Retention(RetentionPolicy.RUNTIME)
@interface MyRepeatableContainer {
    // 定义重复注解容器的属性
    MyRepeatableAnnotation[] value();
}
