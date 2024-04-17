package com.moon.mockito.service;

import com.moon.mockito.dao.DemoDao;

/**
 * 用于测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 12:31
 * @description
 */
public class DemoService {

    private DemoDao demoDao;

    public DemoService(DemoDao demoDao) {
        this.demoDao = demoDao;
    }

    public int getDemoStatus(){
        return demoDao.getDemoStatus();
    }

}
