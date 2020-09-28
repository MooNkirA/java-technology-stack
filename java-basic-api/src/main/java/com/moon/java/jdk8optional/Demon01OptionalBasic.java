package com.moon.java.jdk8optional;

import com.moon.java.common.model.User;
import org.junit.Test;

import java.util.Optional;

/**
 * JDK8 新特性 Optional - 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-28 10:18
 * @description
 */
public class Demon01OptionalBasic {

    // 创建测试的Optional容器
    // private final Optional<String> op = Optional.ofNullable("石原里美");
    private final Optional<String> op = Optional.empty();

    /* Optional类的创建方式 */
    @Test
    public void optionalCreateTest() {
        /* 由于Optional的构造方法为private修饰，无法直接使用new构建对象，只能使用Optional提供的静态方法创建 */

        /*
         * 方式一：public static <T> Optional<T> of(T value)
         *   该方法创建一个有值的Optional实例。如果方法传入的对象为null，将会抛出 NPE 异常
         */
        Optional<String> op1 = Optional.of("天锁斩月");
        // Optional<String> op1 = Optional.of(null); // 异常
        System.out.println("op1: " + op1.get());

        /*
         * 方式二：public static <T> Optional<T> ofNullable(T value)
         *   该方法创建一个可以为空的Optional实例。如果对象为null，将会创建不包含值的empty Optional对象实例
         */
        Optional<String> op2 = Optional.ofNullable("石原里美");
        // Optional<String> op2 = Optional.ofNullable(null); // 创建Optional实例时不会报错
        // 如果是空的Optional对象实例，直接调用get()方法会抛出 NoSuchElementException 异常
        System.out.println("op2: " + op2.get());

        /*
         * 方式三：public static<T> Optional<T> empty()
         *   该方法创创建一个空的Optional实例。等同于 Optional.ofNullable(null)
         */
        Optional<String> op3 = Optional.empty();
        // 如果是空的Optional对象实例，直接调用get()方法会抛出 NoSuchElementException 异常
        // System.out.println("op3: " + op3.get());
        System.out.println("op3是否有值: " + op3.isPresent());
    }


    /* Optional类的get()与isPresent()方法 */
    @Test
    public void getAndIsPresentTest() {
        // isPresent()方法：用于判断Optional容器中是否有值，有值返回true，没有值返回false
        if (op.isPresent()) {
            // get()方法：用于获取Optional容器中的值，如果有值则返回具体值，没有值就报错
            System.out.println("op的值: " + op.get());
        } else {
            System.out.println("op没有值");
        }
    }

    /* Optional类的orElse()方法 */
    @Test
    public void orElseTest() {
        // orElse()方法：如果Optional容器中有值，就返回该值；如果没有值就返回参数指定的值
        String name = op.orElse("新垣结衣?");
        System.out.println("name = " + name);
    }

    /* Optional类的orElseGet()方法 */
    @Test
    public void orElseGetTest() {
        // orElseGet()方法：如果Optional容器中有值，就返回该值；如果没有值就返回参数的Supplier接口提供的值
        String name = op.orElseGet(() -> "长泽雅美");
        System.out.println("name = " + name);
    }

    /* Optional类的orElseThrow()方法 */
    @Test
    public void orElseThrowTest() {
        // orElseThrow()方法：如果Optional容器中有值，就返回该值；如果没有值就抛出由Supplier继承的异常
        String name = op.orElseThrow(NullPointerException::new);
        System.out.println("name = " + name);
    }

    /* Optional类的ifPresent()方法 */
    @Test
    public void ifPresentTest() {
        // ifPresent()方法：如果Optional容器中有值，使用该值调用Consumer接口进行消费处理，否则不做任何事情
        op.ifPresent(s -> System.out.println("name = " + s));

        /*
         * 番外：JDK9 对Optional类做了增强，增加了ifPresentOrElse方法
         *  该方法可以定义容器分别是否为空时的相应处理逻辑
         *  参数1：当前容器有值时，执行此消费方法逻辑
         *  参数2：当前容器为空时，执行此方法逻辑
         */
        /*op.ifPresentOrElse(s -> {
            System.out.println("有值: " + s);
        }, () -> {
            System.out.println("没有值");
        });*/
    }

    /* Optional类的map()方法 */
    @Test
    public void mapTest() {
        // 示例：将用户对象的用户名转成大写并返回
        // User user = null;
        // User user = new User(null, 18);
        User user = new User("mooN", 18);

        // map()方法：如果Optional容器中有值，则执行参数的Function接口实现逻辑；如没有值则返回空的Optional对象实例
        /*String userName = Optional.ofNullable(user)
                .map(u -> u.getUserName())
                .map(s -> s.toUpperCase())
                .orElse("null");*/

        /* 使用方法引用简化上面的代码 */
        String userName = Optional.ofNullable(user) // 创建User对象的Optional容器
                .map(User::getUserName) // 如果User实例不为空，则调用gteUserName方法获取用户名称，否则返回空Optional实例
                .map(String::toUpperCase) // 如果userName不为空，则调用toUpperCase方法转成大写，否则返回空Optional实例
                .orElse("null"); // 如果上述其中一步返回空的Optional实例，则会执行则

        System.out.println("用户名转成大写后值为：" + userName);
    }

    /* Optional类的filter()方法 */
    @Test
    public void filterTest() {
        // 示例：如果用户对象的用户名称不为空且长度大于3，则输出结果
        // User user = null;
        // User user = new User(null, 18);
        User user = new User("mooN", 18);

        // filter()方法：如果Optional容器中有值，并且这个值匹配参数的Predicate接口实现逻辑；返回一个包含此值的Optional实例，否则返回一个空的Optional实例
        Optional.ofNullable(user) // 创建User对象的Optional容器
                .filter(u -> {
                    String userName = u.getUserName();
                    return userName != null && userName.length() > 3;
                }) // 如果user对象不为空，则将user对象去匹配参数的Predicate接口实现逻辑，如果条件成功则返回user对象的Optional实例，否则返回空Optional实例
                .ifPresent(u -> System.out.println("用户名: " + u.getUserName())); // 如果上述其中一步返回空的Optional实例，则会执行则
    }

}
