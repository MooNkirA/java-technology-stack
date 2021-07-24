package com.moon.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Set 接口 API 使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-05-29 18:01
 * @description
 */
public class SetBasicDemo {

    @Test
    public void setBasicApiTest() {

    }

    /* 使用 add 方法判断是否重复值 */
    @Test
    public void repeatingValuesTest() {
        Set<String> set = new HashSet<>();
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map;

        // 用于比较重复
        map = new HashMap<>();
        map.put("id", String.valueOf(8));
        map.put("name", "Moon" + 8);
        map.put("alias", "N" + 8);
        list.add(map);

        for (int i = 0; i < 10; i++) {
            map = new HashMap<>();
            map.put("id", String.valueOf(i));
            map.put("name", "Moon" + i);
            map.put("alias", "N" + i);
            list.add(map);
        }

        for (Map<String, String> m : list) {
            // 出现重复断
            String s = m.get("id") + m.get("name") + m.get("alias");
            System.out.println(s);
            if (!set.add(s)) {
                System.out.println("break");
                break;
            }
        }
    }
}
