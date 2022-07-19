package com.moon.gradle.service;

import com.moon.gradle.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-23 16:57
 * @description
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public String save() {
        return userDao.save();
    }
}
