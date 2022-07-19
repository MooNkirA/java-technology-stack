package com.moon.activiti.test;

import com.moon.activiti.pojo.Evection;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Activiti 流程变量测试 - 启动流程时设置流程变量
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-12 14:15
 * @description
 */
public class ActivitiVariables1Test {

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
                .name("出差申请流程-global")
                .addClasspathResource("bpmn/evection-global.bpmn20.xml")
                .deploy();
        // 输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());
    }

    /**
     * 启动流程，设置相应的流程变量。包括：
     * 1.设置流程变量num
     * 2.设置任务负责人
     */
    @Test
    public void testStartProcess() {
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 流程变量的map
        Map<String, Object> variables = new HashMap<>();
        // 设置流程变量 Pojo
        Evection evection = new Evection();
        // 设置出差日期(走 “部门经理审批” -> "财务审批" 分支)
        // evection.setNum(2d);
        // 设置出差日期(走 “部门经理审批” -> “总经理审批” -> "财务审批" 分支)
        evection.setNum(5d);
        // 把流程变量的pojo放入map
        variables.put("evection", evection);
        // 设置任务负责人
        variables.put("assignee0", "king");
        variables.put("assignee1", "王经理");
        variables.put("assignee2", "杨总经理");
        variables.put("assignee3", "张财务");
        // 根据流程定义Id（其实是数据库表 act_re_procdef 的 key 字段），并设置流程变量，启动流程
        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey("evection-global", variables);
        // 输出流程信息
        System.out.println("流程定义ID：" + instance.getProcessDefinitionId());
        System.out.println("流程实例ID：" + instance.getId());
        System.out.println("当前活动的ID：" + instance.getActivityId());
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completTask() {
        // 任务负责人（根据当前流程的任务来修改相应的名称）
        // String assingee = "king";
        // String assingee = "王经理";
        // String assingee = "杨总经理";
        String assingee = "张财务";
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 根据流程定义key与任务负责人来查询任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection-global")
                .taskAssignee(assingee)
                .singleResult();
        if (task != null) {
            // 根据任务id来 完成任务
            taskService.complete(task.getId());
        }
    }

}
