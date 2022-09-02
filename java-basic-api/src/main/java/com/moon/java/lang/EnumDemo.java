package com.moon.java.lang;

/**
 * Enum 类使用的两条建议
 * <p>
 * 1. 枚举类名带上 `Enum` 后缀，枚举成员名称需要全大写，单词间用下划线隔开。
 * 2. 如果变量值仅在一个固定范围内变化用 `enum` 类型来定义。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-08-09 21:53
 * @description
 */
public class EnumDemo {


    // 普通枚举
    enum ColorEnum {
        RED, GREEN, BLUE;
    }

    // 带属性的枚举，示例中的数字就是延伸信息，表示一年中的第几个季节。
    enum SeasonEnum {
        SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);

        private final int seq;

        SeasonEnum(int seq) {
            this.seq = seq;
        }

        public int getSeq() {
            return seq;
        }
    }
}
