package com.moon.mockito.dao;

import java.util.Random;

/**
 * 用于测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 12:32
 * @description
 */
public class DemoDao {

    public int getDemoStatus() {
        return new Random().nextInt();
    }

}
