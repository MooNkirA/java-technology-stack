package com.moon.java.common.test;

import com.moon.common.model.InitSequenceBean;
import org.junit.Test;

/**
 * Java 基础测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-9 23:00
 * @description
 */
public class BasicTest {

    /* 测试Java类初始化顺序 */
    @Test
    public void testInitializationSequence() {
        InitSequenceBean bean = new InitSequenceBean();
        System.out.println(bean);
        /*
         * 测试结果：
         *  静态成员变量初始化....
         *  静态代码块执行了....
         *  普通成员变量初始化....
         *  初始化代码块执行了....
         *  无参构造函数执行了....
         *  com.moon.java.basic.InitSequenceBean@ba8a1dc
         */
    }

}
