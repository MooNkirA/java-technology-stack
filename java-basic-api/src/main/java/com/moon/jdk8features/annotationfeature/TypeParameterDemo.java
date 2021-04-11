package com.moon.jdk8features.annotationfeature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * JDK8 新特性 - @Target新增类型 TYPE_PARAMETER 的基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-29 09:45
 * @description
 */
// 标识在类上的泛型前
public class TypeParameterDemo<@MyTypeParameter T> {

    // 标识在方法上的泛型前
    public <@MyTypeParameter E extends Integer> void foo() {
    }
}

/**
 * 定义TYPE_PARAMETER类型的注解
 * 表示该注解能写在类型参数的声明语句中。类型参数声明如：<T>
 */
@Target(ElementType.TYPE_PARAMETER)
@interface MyTypeParameter {
}
