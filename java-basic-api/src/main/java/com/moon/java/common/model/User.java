package com.moon.java.common.model;

import java.util.StringJoiner;

/**
 * 用户实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-28 13:36
 * @description
 */
public class User {

    private String userName;
    private int age;

    public User() {
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userName='" + userName + "'")
                .add("age=" + age)
                .toString();
    }
}
