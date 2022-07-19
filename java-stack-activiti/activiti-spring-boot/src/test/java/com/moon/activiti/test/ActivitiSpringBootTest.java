package com.moon.activiti.test;

import com.moon.activiti.utils.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Activiti 整合 Spring Boot  测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-17 11:34
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiSpringBootTest {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiSpringBootTest.class);

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 查看流程定义内容。
     * Activiti7 可以自动部署流程
     */
    @Test
    public void testQueryProcessDefinition() {
        // 需要先登陆用户
        securityUtil.logInAs("jack");
        // 流程定义的分页对象
        Page<ProcessDefinition> definitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        LOGGER.info("可用的流程定义总数：{}", definitionPage.getTotalItems());
        for (ProcessDefinition processDefinition : definitionPage.getContent()) {
            LOGGER.info("==============================");
            LOGGER.info("流程定义内容：{}", processDefinition);
            LOGGER.info("==============================");
        }
    }

    /**
     * 启动流程
     */
    @Test
    public void testStartProcess() {
        // 设置登录用户
        securityUtil.logInAs("system");
        ProcessInstance processInstance = processRuntime.start(
                ProcessPayloadBuilder.start()
                        .withProcessDefinitionKey("activiti-springboot-demo")
                        .build()
        );
        LOGGER.info("流程实例的内容，{}", processInstance);
    }

    /**
     * 执行任务
     */
    @Test
    public void testCompleteTask() {
        // 设置登录用户
        securityUtil.logInAs("jerry");
        // 查询任务
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
        if (taskPage != null && taskPage.getTotalItems() > 0) {
            for (Task task : taskPage.getContent()) {
                // 拾取任务
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                LOGGER.info("任务内容,{}", task);
                // 完成任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
            }
        }
    }

}
