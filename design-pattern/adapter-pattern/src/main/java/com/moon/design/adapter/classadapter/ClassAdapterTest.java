package com.moon.design.adapter.classadapter;

import com.moon.design.adapter.Target;

/**
 * 类适配器模式测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 11:06
 * @description
 */
public class ClassAdapterTest {

    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        // 创建适配器
        Target target = new ClassAdapter();
        // 调用目标接口方法，实际是调用了被适配类中相应的方法
        target.request();
    }

}
