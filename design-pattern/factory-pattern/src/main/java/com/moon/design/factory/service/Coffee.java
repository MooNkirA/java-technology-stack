package com.moon.design.factory.service;

/**
 * 咖啡接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 18:44
 * @description
 */
public interface Coffee {

    /**
     * 获取名字
     *
     * @return
     */
    String getName();

    /**
     * 加牛奶
     */
    void addMilk();

    /**
     * 加糖
     */
    void addSuqar();
}
