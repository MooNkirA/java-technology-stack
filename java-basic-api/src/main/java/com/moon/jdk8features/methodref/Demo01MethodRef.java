package com.moon.jdk8features.methodref;

import com.moon.common.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * JDK8 新特性：方法引用基础使用示例
 * <p>
 * 方法引用有两个注意事项：
 * <p>1.被引用的方法，参数要和接口中抽象方法的参数一样
 * <p>2.当接口抽象方法有返回值时，被引用的方法也必须有返回值
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-26 00:30
 * @description
 */
public class Demo01MethodRef {

    /* 对象::实例方法 - 方法引用示例 */
    @Test
    public void methodReftest01() {
        Date now = new Date();
        // Lambda表达式实现函数式接口
        // Supplier<Long> supplier = () -> now.getTime();

        // 使用方法引用对象实例方法，实现函数式接口
        Supplier<Long> supplier = now::getTime;

        Long time = supplier.get();
        System.out.println("time: " + time);
    }

    /* 类名::静态方法 - 方法引用示例 */
    @Test
    public void test02() {
        // Lambda表达式实现函数式接口
        // Supplier<Long> supplier = () -> System.currentTimeMillis();

        // 使用方法引用类静态方法，实现函数式接口
        Supplier<Long> supplier = System::currentTimeMillis;

        Long time = supplier.get();
        System.out.println("time = " + time);
    }

    /* 类名::实例方法 - 方法引用示例 */
    @Test
    public void test03() {
        // Lambda表达式实现函数式接口(一个参数)
        // Function<String, Integer> f1 = str -> str.length();

        // 使用方法引用类实例方法，实现函数式接口(注意:类名::实例方法实际上会将第一个参数作为方法的调用者)
        Function<String, Integer> f1 = String::length;

        int length = f1.apply("hello");
        System.out.println("length = " + length);

        // Lambda表达式实现函数式接口(两个参数)
        // BiFunction<String, Integer, String> f2 = (String str, Integer index) -> str.substring(index);

        // 使用方法引用类实例方法，实现函数式接口
        BiFunction<String, Integer, String> f2 = String::substring;

        String str2 = f2.apply("helloworld", 3);
        System.out.println("str2 = " + str2);
    }

    /* 类名::new引用类的构造器 - 方法引用示例 */
    @Test
    public void test04() {
        // Lambda表达式实现函数式接口
        // Supplier<Person> supplier1 = () -> new Person();
        // 使用方法引用类构造器方法，实现函数式接口
        Supplier<Person> supplier1 = Person::new;

        Person person = supplier1.get();
        System.out.println("person = " + person);

        // Lambda表达式实现函数式接口
        // BiFunction<String, Integer, Person> bif = (String name, Integer age) -> new Person(name, age);
        // 使用方法引用类构造器方法（有参构造），实现函数式接口。方法引用时，会根据参数列表的个数，引用相应的构造方法
        BiFunction<String, Integer, Person> bif = Person::new;

        Person person2 = bif.apply("新垣结衣", 18);
        System.out.println("person2 = " + person2);
    }

    /* 类型[]::new - 方法引用示例 */
    @Test
    public void test05() {
        // Lambda表达式实现函数式接口
        // Function<Integer, int[]> f = (Integer length) -> new int[length];
        // 使用方法引用数组构造器方法
        Function<Integer, int[]> f = int[]::new;

        int[] arr = f.apply(10);
        System.out.println(Arrays.toString(arr));
    }

}
