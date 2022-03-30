package com.moon.elasticjobdemo.config;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.moon.elasticjobdemo.job.MyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * elastic-job 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-03-30 10:16
 * @description
 */
@Configuration
public class ElasticJobConfig {

    @Autowired
    private MyJob myJob;

    @Autowired
    private ZookeeperRegistryCenter zkRegistryCenterConfig;

    // 读取配置文件：分片数量
    @Value("${myjob.count}")
    private int shardingCount;
    // 读取配置文件：cron 表达式(定时策略)
    @Value("${myjob.cron}")
    private String cron;

    @Bean(initMethod = "init")
    public SpringJobScheduler initSimpleElasticJob() {
        // 创建作业核心配置 JobCoreConfiguration.Builder
        JobCoreConfiguration.Builder jobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(MyJob.class.getName(), cron, shardingCount);
        JobCoreConfiguration jobCoreConfiguration = jobCoreConfigurationBuilder.build();

        // 创建 SIMPLE 类型配置 SimpleJobConfiguration
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, MyJob.class.getCanonicalName());

        // 创建 Lite 作业根配置 LiteJobConfiguration
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration
                .newBuilder(simpleJobConfiguration)
                .jobShardingStrategyClass("com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy") // 配置作业分片策略：平均分配策略
                .overwrite(true)
                .build();

        // 创建 SpringJobScheduler，任务启动
        return new SpringJobScheduler(myJob, zkRegistryCenterConfig, liteJobConfiguration);
    }

}
