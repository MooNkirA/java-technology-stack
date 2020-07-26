package com.moon.jvm.model;

import java.util.StringJoiner;

/**
 * 实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-26 16:59
 * @description
 */
public class Person {

    private String name;
    private String sexType;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("sexType='" + sexType + "'")
                .add("age=" + age)
                .toString();
    }
}
