package com.moon.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Activiti 使用 UEL 表达式配置任务负责人测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-12 14:15
 * @description
 */
public class ActivitiAssigneeUelTest {

    /**
     * 流程部署
     */
    @Test
    public void testDeployment() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RepositoryService 资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、使用 service 进行流程的部署，定义一个流程的名字，把 bpmn 部署到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程-uel")
                .addClasspathResource("bpmn/evection-uel.bpmn20.xml")
                .deploy();
        // 输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());
    }

    /**
     * 启动流程
     */
    @Test
    public void testStartProcess() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3、设定 assignee 的值，用来替换uel表达式
        Map<String, Object> assigneeMap = new HashMap<>();
        assigneeMap.put("assignee0", "MooN");
        assigneeMap.put("assignee1", "李经理");
        assigneeMap.put("assignee2", "王总经理");
        assigneeMap.put("assignee3", "赵财务");
        // 4、根据流程定义Id（其实是数据库表 act_re_procdef 的 key 字段），并设置流程变量，启动流程
        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey("myEvection-uel", assigneeMap);
        // 输出流程信息
        System.out.println("流程定义ID：" + instance.getProcessDefinitionId());
        System.out.println("流程实例ID：" + instance.getId());
        System.out.println("当前活动的ID：" + instance.getActivityId());
    }

}
