package com.moon.java.util.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * JDK8 新特性 Stream 快速入门示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-26 10:08
 * @description
 */
public class Demo01Quickstart {

    private final static ArrayList<String> list = new ArrayList<>();

    static {
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");
    }

    /**
     * JDK8 之前操作集合的传统做法
     */
    @Test
    public void handleListBeforeJDK8() {
        // 1.拿到所有姓张的数据
        ArrayList<String> zhangList = new ArrayList<>(); // {"张无忌", "张强", "张三丰"}
        for (String name : list) {
            if (name.startsWith("张")) {
                zhangList.add(name);
            }
        }

        // 2.拿到名字长度为3个字的数据
        ArrayList<String> threeList = new ArrayList<>(); // {"张无忌", "张三丰"}
        for (String name : zhangList) {
            if (name.length() == 3) {
                threeList.add(name);
            }
        }

        // 3.打印这些数据
        for (String name : threeList) {
            System.out.println(name);
        }
    }

    /**
     * JDK8 使用Stream流操作集合
     */
    @Test
    public void handleListOnJDK8() {
        // 使用stream操作集合
        list.stream()
                .filter(s -> s.startsWith("张")) // 拿到所有姓张的数据 {"张无忌", "张强", "张三丰"}
                .filter(s -> s.length() == 3) // 拿到名字长度为3个字的数据 {"张无忌", "张三丰"}
                .forEach(System.out::println); // 打印数据
    }

}
