package com.moon.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * Activiti 组任务测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-15 10:45
 * @description
 */
public class ActivitiTaskCandidateTest {

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
                .name("出差申请流程-Candidate")
                .addClasspathResource("bpmn/evection-candidate.bpmn20.xml")
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
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 根据流程定义Id（其实是数据库表 act_re_procdef 的 key 字段），并设置流程变量，启动流程
        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey("evection-candidate");
        // 输出流程信息
        System.out.println("流程定义ID：" + instance.getProcessDefinitionId());
        System.out.println("流程实例ID：" + instance.getId());
        System.out.println("当前活动的ID：" + instance.getActivityId());
    }

    /**
     * 查询组任务（注，需要当前流程执行到的任务是有设置了任务候选人）
     */
    @Test
    public void testQueryTaskCandidateUser() {
        // 任务候选人
        String candidateUser = "kirA";
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 根据候选人查询组任务
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("evection-candidate")
                .taskCandidateUser(candidateUser) // 根据候选人查询任务
                .list();
        // 循环数据
        for (Task task : tasks) {
            System.out.println("========================");
            System.out.println("流程实例ID=" + task.getProcessInstanceId());
            System.out.println("任务id=" + task.getId());
            System.out.println("任务负责人=" + task.getAssignee());
        }
    }

    /**
     * 拾取(claim)任务
     */
    @Test
    public void testClaimTask() {
        // 当前任务的id
        String taskId = "82502";
        // 待拾取任务的候选人
        String candidateUser = "kirA";

        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 根据候选人查询组任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(candidateUser) // 根据候选人查询任务
                .singleResult();
        if (task != null) {
            // 根据任务ID，并指定任务候选人，拾取任务
            taskService.claim(taskId, candidateUser);
            System.out.println("taskId- " + taskId + " -用户- " + candidateUser + " -拾取任务完成");
        }
    }

    /**
     * 查询个人任务
     */
    @Test
    public void testQueryAssigneeTask() {
        // 任务负责人
        String assignee = "kirA";
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 根据负责人查询任务
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("evection-candidate")
                .taskAssignee(assignee)
                .list();
        // 循环数据
        for (Task task : tasks) {
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    /**
     * 归还组任务
     */
    @Test
    public void testAssigneeToGroupTask() {
        // 当前任务的id
        String taskId = "82502";
        // 任务的负责人
        String assignee = "kirA";

        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 根据负责人查询组任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)
                .singleResult();
        if (task != null) {
            // 根据任务ID，归还任务，实质就是将负责人 assignee 设置为 null
            taskService.setAssignee(taskId, null);
            System.out.println("taskId- " + taskId + " -负责人- " + assignee + " -归还任务完成");
        }
    }

    /**
     * 组任务交接
     */
    @Test
    public void testAssigneeToCandidateUser() {
        // 当前任务的id
        String taskId = "82502";
        // 任务的负责人
        String assignee = "kirA";
        // 待交接的候选人
        String candidateUser = "Zero";

        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 根据负责人查询组任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(assignee)
                .singleResult();
        if (task != null) {
            // 根据任务ID，交接任务，实质就是将负责人 assignee 设置为候选人
            taskService.setAssignee(taskId, candidateUser);
            System.out.println("taskId- " + taskId + " -负责人- " + assignee + " -交接任务给候选人- " + candidateUser);
        }
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completTask() {
        // 任务负责人（根据当前流程的任务来修改相应的名称）
        String assingee = "金田一";
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 根据流程定义key与任务负责人来查询任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection-candidate")
                .taskAssignee(assingee)
                .singleResult();
        if (task != null) {
            // 根据任务id来 完成任务
            taskService.complete(task.getId());
            System.out.println(task.getAssignee() + " 完成任务");
        }
    }

}
