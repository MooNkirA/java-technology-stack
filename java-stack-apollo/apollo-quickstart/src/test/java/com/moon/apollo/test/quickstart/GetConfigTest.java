package com.moon.apollo.test.quickstart;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * apollo 快速入门测试 - 读取发布的配置项
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-4 17:16
 * @description
 */
public class GetConfigTest {

    /*
     * 启动时设置VM options:
     *  -Dapp.id=apollo-quickstart -Denv=DEV -Ddev_meta=http://localhost:8080
     *      app.id是对应apollo服务创建的项目AppId
     *      env是对应apollo服务的项目环境
     *      dev_meta是对应apollo-portal服务的url
     */
    @Test
    public void getAppConfigTest() {
        // 获取apollo配置对象，此方法是读取默认namespace: application下的配置信息
        Config config = ConfigService.getAppConfig();
        // 需要获取的配置对应的key
        String key = "sms.enable";
        /*
         * 通过apollo配置对象的getProperty(String key, String defaultValue)方法，获取key的值
         *  参数key：配置项的key
         *  参数defaultValue：配置项的默认值
         */
        String value = config.getProperty(key, null);
        System.out.println("sms.enable: " + value);
    }

    /* 配置实时更新测试 */
    @Test
    public void hotPublishTest() throws InterruptedException {
        // 获取apollo配置对象
        Config config = ConfigService.getAppConfig();
        // 需要获取的配置对应的key
        String key = "sms.enable";
        while (true) {
            // 获取key的值
            String value = config.getProperty(key, null);
            System.out.printf("now: %s, sms.enable: %s%n", LocalDateTime.now().toString(), value);
            Thread.sleep(3000L);
        }
    }

    /* 测试获取指定namespace下的配置信息 */
    @Test
    public void getNamespaceConfigTest() throws InterruptedException {
        // 创建带namespace参数的配置对象
        Config config = ConfigService.getConfig("spring-rocketmq");
        // 需要获取的配置对应的key
        String key = "rocketmq.name-server";
        while (true) {
            // 获取key的值
            String value = config.getProperty(key, null);
            System.out.printf("now: %s, rocketmq.name-server: %s%n", LocalDateTime.now().toString(), value);
            Thread.sleep(3000L);
        }
    }

    /* 测试获取不同群集的配置信息 */
    @Test
    public void getClusterConfigTest() throws InterruptedException {
        // 创建带namespace参数的配置对象
        Config config = ConfigService.getConfig("micro_service.spring-boot-http");
        // 需要获取的配置对应的key
        String key = "server.servlet.context‐path";
        while (true) {
            // 获取key的值
            String value = config.getProperty(key, null);
            System.out.printf("now: %s, server.servlet.context‐path: %s%n", LocalDateTime.now().toString(), value);
            Thread.sleep(3000L);
        }
    }

}
