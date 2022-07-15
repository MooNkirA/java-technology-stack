package com.moon.activiti.spring.config;

import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-15 20:06
 * @description
 */
@Component("taskService")
public class RepositoryServiceFactoryBean implements FactoryBean<TaskService> {

    @Autowired
    private ProcessEngineFactoryBean processEngineFactoryBean;

    @Override
    public TaskService getObject() throws Exception {
        return processEngineFactoryBean.getObject().getTaskService();
    }

    @Override
    public Class<?> getObjectType() {
        return TaskService.class;
    }

}
