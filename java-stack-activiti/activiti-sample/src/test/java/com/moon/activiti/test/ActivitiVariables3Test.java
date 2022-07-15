package com.moon.activiti.test;

import com.moon.activiti.pojo.Evection;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activiti Global 流程变量测试 - 其方式设置流程变量
 * Activiti Local 流程变量测试
 * 注：这些示例都不直接运行，若测试需要做一定的修改
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-12 14:15
 * @description
 */
public class ActivitiVariables3Test {

    /**
     * 通过当前流程实例设置 Global 流程变量
     */
    @Test
    public void testSetGlobalVariableByExecutionId() {
        // 当前流程实例执行 id，通常设置为当前执行的流程实例（注意：必须是未结束的流程实例）
        String executionId = "xxx";
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 设置流程变量 Pojo
        Evection evection = new Evection();
        // 设置出差日期
        evection.setNum(5d);

        /*
         * 通过流程运行管理类的方法，给指定id的流程设置单个流程变量
         * public void setVariable(String executionId, String variableName, Object value)
         *  executionId 参数：流程实例的ID
         *  variableName 参数：流程变量的名称
         *  value 参数：流程变量的值
         */
        runtimeService.setVariable(executionId, "evection", evection);

        /*
         * 通过流程运行管理类的方法，给指定id的流程一次性设置多个流程变量
         * public void setVariables(String executionId, Map<String, ? extends Object> variables)
         *  executionId 参数：流程实例的ID
         *  variables 参数：多个流程变量 k-v 集合
         */
        // 流程变量的map
        Map<String, Object> variables = new HashMap<>();
        // 把流程变量的pojo放入map
        variables.put("evection", evection);
        // 设置其他变量
        variables.put("name", "MooN");
        variables.put("time", "12");
        runtimeService.setVariables(executionId, variables);
    }

    /**
     * 通过当前任务设置 Global 流程变量
     */
    @Test
    public void testSetGlobalVariableByTaskId() {
        // 当前待办任务id（注意：必须是未完成的任务）
        String taskId = "xxx";
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 设置流程变量 Pojo
        Evection evection = new Evection();
        // 设置出差日期
        evection.setNum(5d);

        /*
         * 通过任务管理类的方法，给指定id的任务设置单个流程变量
         * public void setVariable(String taskId, String variableName, Object value)
         *  taskId 参数：未完成的任务ID
         *  variableName 参数：流程变量的名称
         *  value 参数：流程变量的值
         */
        taskService.setVariable(taskId, "evection", evection);

        /*
         * 通过任务管理类的方法，给指定id的任务一次性设置多个流程变量
         * public void setVariables(String taskId, Map<String, ? extends Object> variables)
         *  executionId 参数：未完成的任务ID
         *  variables 参数：多个流程变量 k-v 集合
         */
        // 流程变量的map
        Map<String, Object> variables = new HashMap<>();
        // 把流程变量的pojo放入map
        variables.put("evection", evection);
        // 设置其他变量
        variables.put("name", "MooN");
        variables.put("time", "12");
        taskService.setVariables(taskId, variables);
    }

    /**
     * 任务办理时设置 local 流程变量。
     * <p>
     * Tips:
     * - 任务办理时设置 local 流程变量，当前运行的流程实例只能在该任务结束前使用，任务结束后该变量无法再使用，可以通过查询历史任务查询。
     * - 设置作用域为任务的 local 变量，每个任务可以设置同名的变量，互不影响
     * </p>
     */
    @Test
    public void testSetLocalVariableOnCompletTask() {
        // 任务id
        String taskId = "1404";
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 定义流程变量 map
        Map<String, Object> variables = new HashMap<>();
        Evection evection = new Evection();
        evection.setNum(3d);
        // 变量名是 evection，变量值是 POJO 对象
        variables.put("evection", evection);
        // 设置为 local 变量，作用域为指定ID的任务
        taskService.setVariablesLocal(taskId, variables);
        // 完成任务
        taskService.complete(taskId);
    }

    /**
     * 通过当前任务设置 local 流程变量。
     * Tips: 通过当前任务设置 local 流程变量，任务id 必须是当前待办任务，即在 act_ru_task 表中存在
     */
    @Test
    public void testSetLocalVariableByTaskId() {
        // 当前待办任务id
        String taskId = "1404";
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 设置流程变量 Pojo
        Evection evection = new Evection();
        // 设置出差日期
        evection.setNum(5d);

        /*
         * 通过任务管理类的方法，给指定id的任务设置单个 local 流程变量
         * void setVariableLocal(String taskId, String variableName, Object value)
         *  taskId 参数：未完成的任务ID
         *  variableName 参数：流程变量的名称
         *  value 参数：流程变量的值
         */
        taskService.setVariableLocal(taskId, "evection", evection);

        /*
         * 通过任务管理类的方法，给指定id的任务一次性设置多个流程变量
         * void setVariablesLocal(String taskId, Map<String, ? extends Object> variables)
         *  executionId 参数：未完成的任务ID
         *  variables 参数：多个流程变量 k-v 集合
         */
        // 流程变量的map
        Map<String, Object> variables = new HashMap<>();
        // 把流程变量的pojo放入map
        variables.put("evection", evection);
        // 设置其他变量
        variables.put("name", "MooN");
        variables.put("time", "12");
        taskService.setVariablesLocal(taskId, variables);
    }

    /**
     * 查询每个历史任务中，local 流程变量的值
     */
    @Test
    public void testQueryLocalVariableHistory() {
        // 创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 TaskService 任务管理类
        HistoryService historyService = processEngine.getHistoryService();
        // 查询包含有 local 变量的历史任务列表
        List<HistoricTaskInstance> historicTaskInstances = historyService
                .createHistoricTaskInstanceQuery() // 创建历史任务查询对象
                .includeTaskLocalVariables() // 查询结果包括 local 变量
                .list();
        // 循环数据
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            System.out.println("==============================");
            System.out.println("任务id：" + historicTaskInstance.getId());
            System.out.println("任务名称：" + historicTaskInstance.getName());
            System.out.println("任务负责人：" + historicTaskInstance.getAssignee());
            System.out.println("任务local变量：" + historicTaskInstance.getTaskLocalVariables());

        }
    }
}
