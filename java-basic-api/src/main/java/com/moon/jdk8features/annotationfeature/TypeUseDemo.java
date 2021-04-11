package com.moon.jdk8features.annotationfeature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * JDK8 新特性 - @Target新增类型 TYPE_USE 的基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-29 09:46
 * @description
 */
public class TypeUseDemo {

    // 在类属性类型前使用
    private @MyTypeUse int a = 10;

    // 在方法形参的类型前使用
    public void test(@MyTypeUse String str, @MyTypeUse int a) {
        // 在方法内的变量类型前使用
        @MyTypeUse double d = 10.1;
    }
}

/**
 * 定义TYPE_USE类型的注解
 * 表示注解可以再任何用到类型的地方使用
 */
@Target(ElementType.TYPE_USE)
@interface MyTypeUse {
}
