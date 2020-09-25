package com.moon.java.lambda;

import com.moon.java.common.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Lambda表达式基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 08:22
 * @description
 */
public class LambdaDemo02Basic {

    /* 01 无参数无返回值的Lambda */
    @Test
    public void lambdaNoParamsTest() {
        // 传统写法：匿名内部类方式实现
        playBasketball(new Sportable() {
            @Override
            public void doSport() {
                System.out.println("使用匿名内部类方式调用playBasketball(Sportable sportable)方法...");
            }
        });
        System.out.println("---------------------------------");
        /*
         * Lambda表达式方式实现
         *  相当于是对接口抽象方法的重写
         */
        playBasketball(() -> System.out.println("使用Lambda表达式方式调用playBasketball(Sportable sportable)方法..."));

    }

    // 定义方法，入参为Sportable接口，方法体中调用Sportable接口的doSport()方法
    private void playBasketball(Sportable sportable) {
        sportable.doSport();
    }

    /* 02 有参数有返回值的Lambda */
    @Test
    public void lambdaHasParamsTest() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("石原里美", 30, 156));
        persons.add(new Person("新垣结衣", 28, 168));
        persons.add(new Person("天锁斩月", 183, 180));
        persons.add(new Person("樱木花道", 18, 189));

        // 传统写法：匿名内部类方式实现
        /*Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // 返回对象年龄属性的差值，可以实现按年龄排序
                return o1.getAge() - o2.getAge();
            }
        });*/

        // Lambda表达式方式实现，标准格式
        Collections.sort(persons, (Person o1, Person o2) -> {
            return o1.getAge() - o2.getAge();
        });

        // 输入结果
        for (Person person : persons) {
            System.out.println(person);
        }
    }

}

/*
 * 定义示例使用的接口
 */
interface Sportable {
    void doSport();
}
