package com.moon.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * Activiti 关联业务 BusinessKey
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-12 14:15
 * @description
 */
public class ActivitiBusinessDemo {

    /**
     * 添加业务 key 到 Activiti 的 act_ru_execution 表
     */
    @Test
    public void testAddBusinessKey() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        /*
         * 3、启动流程时关联 businesskey
         *  ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey);
         *      processDefinitionKey 参数：流程定义的key
         *      businessKey 参数：项目中其他业务的唯一标识，如示例是出差申请单的id="1001"
         */
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection", "1001");
        // 输出结果
        System.out.println("businessKey == " + instance.getBusinessKey());
    }

    /**
     * 查询运行的流程实例
     */
    @Test
    public void testQueryProcessInstance() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3、根据流程定义key查询
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey("myEvection")
                .list();
        // 循环数据
        for (ProcessInstance processInstance : processInstances) {
            System.out.println("----------------------------");
            System.out.println("流程实例id：" + processInstance.getProcessInstanceId());
            System.out.println("所属流程定义id：" + processInstance.getProcessDefinitionId());
            System.out.println("是否执行完成：" + processInstance.isEnded());
            System.out.println("是否暂停：" + processInstance.isSuspended());
            System.out.println("当前活动标识：" + processInstance.getActivityId());
        }
    }

    /**
     * 全部流程实例的挂起与激活
     */
    @Test
    public void testSuspendAllProcessInstance() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RepositoryService 资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、获取流程定义的查询对象，查询流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();
        // 4、获取当前流程定义的所有实例是否都为暂停状态
        boolean suspended = processDefinition.isSuspended();
        String definitionId = processDefinition.getId(); // 获取流程定义的id
        // 5、判断流程实例的状态
        if (suspended) {
            /*
             * 如果是挂起状态，则执行激活的操作
             *  public void activateProcessDefinitionById(String processDefinitionId, boolean activateProcessInstances, Date activationDate)
             *      processDefinitionId 参数：流程定义id
             *      activateProcessInstances 参数：是否激活
             *      activationDate 参数：流程开始激活的日期，如果为 null，则流程立即激活
             */
            repositoryService.activateProcessDefinitionById(definitionId, true, null);
            System.out.println("流程定义id:" + definitionId + "，已激活");
        } else {
            /*
             * 如果是激活状态，则执行挂起的操作
             *  public void suspendProcessDefinitionById(String processDefinitionId, boolean suspendProcessInstances, Date suspensionDate)
             *      processDefinitionId 参数：流程定义id
             *      suspendProcessInstances 参数：是否激活
             *      suspensionDate 参数：流程开始挂起的日期，如果为 null，则流程立即挂起
             */
            repositoryService.suspendProcessDefinitionById(definitionId, true, null);
            System.out.println("流程定义id:" + definitionId + "，已挂起");
        }
    }

    /**
     * 单个流程实例的挂起与激活
     */
    @Test
    public void testSuspendSingleProcessInstance() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3、通过 RuntimeService 获取某个流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("10001")
                .singleResult();
        // 4、获取当前流程实例的暂停状态。true-已暂停  false-激活
        boolean suspended = processInstance.isSuspended();
        String instanceId = processInstance.getId(); // 获取流程实例的id
        // 5、判断流程实例的状态
        if (suspended) {
            // 如果是挂起状态，则执行激活的操作
            runtimeService.activateProcessInstanceById(instanceId);
            System.out.println("流程实例id:" + instanceId + "已经激活");
        } else {
            // 如果是激活状态，则执行挂起的操作
            runtimeService.suspendProcessInstanceById(instanceId);
            System.out.println("流程实例id:" + instanceId + "已经暂停");
        }
    }

    /**
     * 用于测试完成挂起的任务是否会报错
     */
    @Test
    public void testCompletSuspendTask() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 3、使用 TaskService 根据流程实例的id、负责人来获取任务
        Task task = taskService.createTaskQuery()
                .processInstanceId("10001")
                .taskAssignee("Sam")
                .singleResult();
        System.out.println("流程实例id==" + task.getProcessInstanceId());
        System.out.println("流程任务id==" + task.getId());
        System.out.println("负责人==" + task.getAssignee());
        System.out.println("任务名称==" + task.getName());
        // 4、根据任务的id完成任务
        taskService.complete(task.getId());
    }

}
