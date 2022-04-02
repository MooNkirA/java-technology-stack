package com.moon.elasticjobdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-03-30 9:06
 * @description
 */
@MapperScan("com.moon.elasticjobdemo.dao")
@SpringBootApplication
public class ElasticJobApp {

    public static void main(String[] args) {
        SpringApplication.run(ElasticJobApp.class, args);
    }

}
