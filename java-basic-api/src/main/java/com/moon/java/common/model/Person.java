package com.moon.java.common.model;

import java.util.StringJoiner;

/**
 * 人实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-24 23:15
 * @description
 */
public class Person {

    private String name;
    private int age;
    private int height;

    public Person() {
    }

    public Person(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("height=" + height)
                .toString();
    }
}
