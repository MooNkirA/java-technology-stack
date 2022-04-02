package com.moon.elasticjobdemo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.moon.elasticjobdemo.dao.UserMapper;
import com.moon.elasticjobdemo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 具体的任务实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-03-30 9:59
 * @description
 */
@Component
@Slf4j
public class MyJob implements SimpleJob {

    @Autowired
    private UserMapper userMapper;

    /**
     * 执行作业的主要业务逻辑
     *
     * @param shardingContext 分片上下文
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        // 从 Elastic-Job 分片上下文中，获取分片总数
        int shardingTotalCount = shardingContext.getShardingTotalCount();
        // 获取当前分片项
        int shardingItem = shardingContext.getShardingItem();

        // 查询结果
        List<User> users = userMapper.queryUserById(shardingTotalCount, shardingItem);
        for (User user : users) {
            log.info("作业分片: {} ---> {}", shardingItem, user);
        }
    }

}
