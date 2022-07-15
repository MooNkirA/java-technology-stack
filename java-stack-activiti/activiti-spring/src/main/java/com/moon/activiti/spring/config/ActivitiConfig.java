package com.moon.activiti.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Activiti 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-15 19:33
 * @description
 */
@Configuration
@ComponentScan("com.moon.activiti")
public class ActivitiConfig {

    /* 配置数据源，使用阿里druid数据源 */
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/activiti_sample?useSSL=false");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        return druidDataSource;
    }

    /* 工作流引擎配置bean */
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DruidDataSource dataSource, DataSourceTransactionManager transactionManager) {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        // 设置数据源
        processEngineConfiguration.setDataSource(dataSource);
        // 设置 spring 事务管理器
        processEngineConfiguration.setTransactionManager(transactionManager);
        // 设置数据库策略
        processEngineConfiguration.setDatabaseSchemaUpdate("drop-create");
        return processEngineConfiguration;
    }

    /* 流程引擎 */
    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        return processEngineFactoryBean;
    }

    /* Spring 事务管理器 */
    @Bean
    public DataSourceTransactionManager transactionManager(DruidDataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
