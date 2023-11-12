package com.moon.concurrent.entity;

import lombok.Data;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-22 22:41
 * @description
 */
@Data
public class Student {

    private volatile int id;
    private volatile String name;

}
