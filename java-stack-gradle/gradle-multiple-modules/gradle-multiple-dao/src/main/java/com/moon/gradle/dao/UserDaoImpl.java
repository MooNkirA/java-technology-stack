package com.moon.gradle.dao;

import org.springframework.stereotype.Repository;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-23 16:55
 * @description
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public String save() {
        return "保存成功！";
    }

}
