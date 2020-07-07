package com.moon.apollo.quickstart;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.time.LocalDateTime;

/**
 * 灰度发布测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-6 21:34
 * @description
 */
public class GrayTest {

    public static void main(String[] args) throws InterruptedException {
        // 获取apollo配置对象
        Config config = ConfigService.getAppConfig();
        // 需要获取的配置对应的key
        String key = "timeout";
        while (true) {
            // 获取key的值
            String value = config.getProperty(key, null);
            System.out.printf("now: %s, timeout: %s%n", LocalDateTime.now().toString(), value);
            Thread.sleep(3000L);
        }
    }

}
