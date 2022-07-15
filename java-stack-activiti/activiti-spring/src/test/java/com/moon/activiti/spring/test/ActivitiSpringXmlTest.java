package com.moon.activiti.spring.test;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于 xml 配置的 Activiti7 与 Spring 整合
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-15 19:20
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti-spring.xml")
public class ActivitiSpringXmlTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void testBasic() {
        System.out.println(repositoryService);
    }

}
