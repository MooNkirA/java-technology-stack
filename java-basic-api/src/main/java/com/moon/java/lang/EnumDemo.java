package com.moon.java.lang;

import java.math.BigDecimal;

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

    // 带抽象方法枚举，示例中的构造方法为类型的中文名称，在定义枚举值时需要实现抽象方法
    enum PayTypeEnum {
        WX_PAY("微信支付") {
            @Override
            public void doPay(BigDecimal money) {
                System.out.println("微信支付: " + money);
            }
        }, ALI_PAY("支付宝支付") {
            @Override
            public void doPay(BigDecimal money) {
                System.out.println("支付宝支付: " + money);
            }
        };

        private final String name;

        PayTypeEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        // 定义抽象方法
        public abstract void doPay(BigDecimal money);
    }
}
