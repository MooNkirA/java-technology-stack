package com.moon.basic.genericity;

/**
 * 实现泛型接口的类情况一：不是泛型类，需要明确实现泛型接口的数据类型。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 11:40
 * @description
 */
public class GenericInterfaceImplFirst implements GenericInterface<String> {

    @Override
    public String getKey() {
        return "Hello GenericInterface implement";
    }

}
