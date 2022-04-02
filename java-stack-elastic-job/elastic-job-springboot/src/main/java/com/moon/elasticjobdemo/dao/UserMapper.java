package com.moon.elasticjobdemo.dao;

import com.moon.elasticjobdemo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user 表数据访问接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-03-30 9:12
 * @description
 */
public interface UserMapper {

    /**
     * 根据分片查询
     *
     * @param shardingTotalCount
     * @param shardingItem
     * @return
     */
    List<User> queryUserById(@Param("shardingTotalCount") int shardingTotalCount, @Param("shardingItem") int shardingItem);

}
