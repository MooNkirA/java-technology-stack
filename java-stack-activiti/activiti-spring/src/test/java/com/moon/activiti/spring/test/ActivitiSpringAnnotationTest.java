package com.moon.activiti.spring.test;

import com.moon.activiti.spring.config.ActivitiConfig;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于纯注解方式配置的 Activiti7 与 Spring 整合
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-15 19:20
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ActivitiConfig.class)
public class ActivitiSpringAnnotationTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void testBasic() {
        System.out.println(taskService);
    }

}
