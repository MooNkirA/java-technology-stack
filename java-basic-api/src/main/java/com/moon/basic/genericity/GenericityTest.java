package com.moon.basic.genericity;

import org.junit.Test;

/**
 * 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 11:21
 * @description
 */
public class GenericityTest {

    // 泛型类基础使用测试
    @Test
    public void test01() {
        // 在使用泛型类的时候去确定泛型类形参的数据类型<数据类型>，Java 1.7以后，右边<>的数据类型可以省略
        Generic<String> strGen = new Generic<>("abc");
        String str = strGen.getKey();
        System.out.println(str);

        // 泛型的类型参数只能是类类型（包括自定义类），不能是基本数据类型
        Generic<Integer> intGen = new Generic<Integer>(123);
        int num = intGen.getKey();
        System.out.println(num);

        // 泛型类如果没有指定数据类型，那么会按 Object 类型来处理
        Generic generic = new Generic("abc");
        Object key = generic.getKey();
        System.out.println(key);
    }

    // 泛型类派生子类基础使用测试
    @Test
    public void test02() {
        // 子类是泛型类，则需要与父类类型保持一致
        ChildFirst<String> childFirst = new ChildFirst<>();
        childFirst.setValue("abc");
        String value = childFirst.getValue();
        System.out.println(value);
        System.out.println("---------------------------------");

        // 子类非泛型类，将泛型父类需要指定类型
        ChildSecond childSecond = new ChildSecond();
        childSecond.setValue(100);
        Integer value1 = childSecond.getValue();
        System.out.println(value1);
    }

    // 泛型接口实现类基础使用测试
    @Test
    public void test03() {
        // 实现类非泛型类，需要明确实现泛型接口的数据类型。
        GenericInterfaceImplFirst genericImpl1 = new GenericInterfaceImplFirst();
        System.out.println(genericImpl1.getKey());

        System.out.println("---------------------------------");
        // 实现类是泛型类，则要保证实现接口的泛型类泛型标识包含泛型接口的泛型标识
        GenericInterfaceImplSecond<String, Integer> genericImpl2 = new GenericInterfaceImplSecond<>("count", 100);
        System.out.println(genericImpl2.getKey() + "=" + genericImpl2.getValue());
    }


}
