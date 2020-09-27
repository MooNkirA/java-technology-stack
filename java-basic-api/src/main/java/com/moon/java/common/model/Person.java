package com.moon.java.common.model;

import java.util.Objects;
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
        System.out.println("执行Person类无参构造");
    }

    public Person(String name) {
        String temp = new StringJoiner(", ", "执行Person类有参构造" + "[", "]")
                .add("name='" + name + "'")
                .toString();
        System.out.println(temp);
        this.name = name;
    }

    public Person(String name, int age) {
        String temp = new StringJoiner(", ", "执行Person类有参构造" + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .toString();
        System.out.println(temp);
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, int height) {
        String temp = new StringJoiner(", ", "执行Person类有参构造" + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("height=" + height)
                .toString();
        System.out.println(temp);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                height == person.height &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height);
    }
}
