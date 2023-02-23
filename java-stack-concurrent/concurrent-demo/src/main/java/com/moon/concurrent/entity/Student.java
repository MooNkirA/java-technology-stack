package com.moon.concurrent.entity;

import java.util.StringJoiner;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-22 22:41
 * @description
 */
public class Student {

    private volatile int id;
    private volatile String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
