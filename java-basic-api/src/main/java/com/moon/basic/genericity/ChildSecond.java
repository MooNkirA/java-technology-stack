package com.moon.basic.genericity;

/**
 * 泛型类派生子类情况二：如果子类不是泛型类，那么父类要明确数据类型
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 11:20
 * @description
 */
public class ChildSecond extends Parent<Integer> {

    @Override
    public Integer getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }

}
