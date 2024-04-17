package com.moon.junit4;

/**
 * 用于参数化测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 18:01
 * @description
 */
public class PrimeNumberChecker {

    public Boolean validate(final Integer parimeNumber) {
        for (int i = 2; i < (parimeNumber / 2); i++) {
            if (parimeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }

}
