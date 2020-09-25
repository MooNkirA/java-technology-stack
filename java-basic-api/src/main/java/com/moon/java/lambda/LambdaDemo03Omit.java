package com.moon.java.lambda;

import com.moon.java.common.model.Person;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Lambda表达式省略格式基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 10:16
 * @description
 */
public class LambdaDemo03Omit {

    /* Lambda表达式省略格式写法示例 */
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("石原里美", 30, 156));
        persons.add(new Person("新垣结衣", 28, 168));
        persons.add(new Person("天锁斩月", 183, 180));
        persons.add(new Person("樱木花道", 18, 189));

        /*
         * Lambda表达式省略格式写法：多个参数，有返回值
         *  1. 小括号内参数的类型可以省略
         *  2. 小括号内参数是多个，则小括号不可以省略
         *  3. 如果大括号内有且仅有一个语句，可以同时省略大括号、return关键字及语句分号
         */
        Collections.sort(persons, (o1, o2) -> o1.getAge() - o2.getAge());

        /*
         * Lambda表达式省略格式写法：单个参数，无返回值
         *  1. 小括号内参数的类型可以省略
         *  2. 小括号内参数只有一个，则小括号可以省略
         *  3. 如果大括号内有且仅有一个语句，可以同时省略大括号、return关键字及语句分号
         */
        persons.forEach(person -> System.out.println(person));
    }

}
