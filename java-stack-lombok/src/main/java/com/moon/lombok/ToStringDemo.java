package com.moon.lombok;

import lombok.ToString;

import java.time.LocalDate;

/**
 * ToString 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 09:03
 * @description `@ToString` 注解可以为指定类生成 toString() 方法
 */
@ToString(exclude = {"dateOfBirth"}) // toString方法不包含dateOfBirth字段
public class ToStringDemo {
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
}
