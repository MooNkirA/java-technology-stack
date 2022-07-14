package com.moon.activiti.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Activiti 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-28 16:34
 * @description
 */
public class ActivitiTest {

    /**
     * 流程部署 - 单个文件部署方式
     */
    @Test
    public void testDeployment() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RepositoryServcie
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、使用 service 进行流程的部署，定义一个流程的名字，把 bpmn 和 png 部署到数据中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程")
                .addClasspathResource("bpmn/evection.bpmn")
                // .addClasspathResource("bpmn/evection.png")
                .deploy();
        // 4、输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());
    }

    /**
     * 流程部署 -  使用zip包进行批量的部署
     */
    @Test
    public void testDeployByZip() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RepositoryServcie
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、读取资源包文件，构造成 InputStream
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("bpmn/evection.zip");
        // 将 InputStream 转成 ZipInputStream
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // 4、使用 RepositoryService 对压缩包的流进行流程部署
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程")
                .addZipInputStream(zipInputStream)
                .deploy();
        // 5、输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字=" + deploy.getName());
    }

    /**
     * 启动流程实例，以下是涉及操作的数据表：
     * <p>
     * - act_hi_actinst：流程实例执行历史
     * - act_hi_identitylink：流程的参与用户历史信息
     * - act_hi_procinst：流程实例历史信息
     * - act_hi_taskinst：流程任务历史信息
     * - act_ru_execution：流程执行信息
     * - act_ru_identitylink：流程的参与用户信息
     * - act_ru_task：任务信息
     * </p>
     */
    @Test
    public void testStartProcess() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RuntimeService 流程运行管理类
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3、根据流程定义Id（其实是数据库表 act_re_procdef 的 key 字段），启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection");
        // 4、输出流程信息
        System.out.println("流程定义ID：" + instance.getProcessDefinitionId());
        System.out.println("流程实例ID：" + instance.getId());
        System.out.println("当前活动的ID：" + instance.getActivityId());
    }

    /**
     * 查询个人待执行任务
     */
    @Test
    public void testTaskQueryByAssignee() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 3、根据流程 key 和 任务的负责人 查询任务
        List<Task> taskQuery = taskService.createTaskQuery()
                .processDefinitionKey("myEvection") // 流程Key
                .taskAssignee("Sam") // 只查询该任务负责人的任务
                .list();
        // 4、输出任务信息
        for (Task task : taskQuery) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    /**
     * 完成个人任务(根据流程定义key查询任务)
     */
    @Test
    public void testCompletTaskByProcessDefinitionKey() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        // 3、先根据流程 key 和 任务的负责人 查询当前任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myEvection") // 流程Key
                .taskAssignee("Sam") // 只查询该任务负责人的任务
                .singleResult();
        // 4、根据任务id，完成任务
        taskService.complete(task.getId());
    }

    /**
     * 完成个人任务(根据任务id查询任务)
     */
    @Test
    public void testCompletTaskByTaskId() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 TaskService 任务管理类
        TaskService taskService = processEngine.getTaskService();
        /*
         * 3、查询任务。完成任务前，需要校验该负责人可以完成当前任务
         *  校验方法：根据任务id和任务负责人查询当前任务，如果查到该用户有权限，就完成
         */
        Task task = taskService.createTaskQuery()
                .taskId("22505") // 任务ID
                .taskAssignee("Sam") // 只查询该任务负责人的任务
                .singleResult();
        // 4、根据任务id，完成任务
        if (task != null) {
            taskService.complete(task.getId());
            System.out.println("完成任务");
        }
    }

    /**
     * 查询流程定义
     */
    @Test
    public void testQueryProcessDefinition() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 Repositoryservice 资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、创建 ProcessDifinitionQuery 对象，用于查询
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        /*
         * 查询当前所有的流程定义，返回流程定义信息的集合
         *   processDefinitionKey 方法是根据流程定义Key 查询
         *   orderByProcessDefinitionVersion 方法是进行排序
         */
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.processDefinitionKey("myEvection")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        // 输出相关信息
        for (ProcessDefinition processDefinition : processDefinitions) {
            System.out.println("流程定义ID：" + processDefinition.getId());
            System.out.println("流程定义名称:" + processDefinition.getName());
            System.out.println("流程定义Key:" + processDefinition.getKey());
            System.out.println("流程定义版本:" + processDefinition.getVersion());
            System.out.println("流程部署ID:" + processDefinition.getDeploymentId());
        }
    }

    /**
     * 删除流程部署信息，删除涉及的表如下：
     * `act_ge_bytearray`
     * `act_re_deployment`
     * `act_re_procdef`
     * notes: 当前的流程如果并没有完成，删除时需要使用级联删除
     */
    @Test
    public void testDeleteDeployMent() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 Repositoryservice 资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、通过查询来获取部署id（此处省略，参考上面的查询示例）
        String deploymentId = "12501";
        // 4、删除流程部署。值得注意：如果该流程部署已有流程实例启动则删除时会报错
        repositoryService.deleteDeployment(deploymentId);
        // 方法第二个参数用于设置是否级联删除流程部署，设置 true 则表示级联删除，否则设置为 false
        // 级联删除意味着该流程即使有流程实例启动也可以删除
        // repositoryService.deleteDeployment(deploymentId, true);
    }

    /**
     * 下载流程定义部署资源文件
     * 方案1： 使用Activiti提供的api，来下载资源文件,保存到文件目录
     * 方案2： 手动编写代码从数据库中下载文件，使用 jdbc 读取 blob 类型数据，保存到文件目录
     * 解决Io操作：commons-io.jar
     * 以下示例使用方案1，Activiti提供的api：RespositoryService
     */
    @Test
    public void testGetDeployMentResources() throws Exception {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 Repositoryservice 资源管理类
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、获取查询对象 ProcessDefinitionQuery 查询流程定义信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();
        // 4、通过流程定义信息，获取部署ID
        String deploymentId = processDefinition.getDeploymentId();
        // 5、通过 RepositoryService 接口根据部署id参数，读取资源信息（png 和 bpmn）
        // 从流程定义表中，获取png图片的目录和名字
        String pngName = processDefinition.getDiagramResourceName();
        // 通过 部署id和 文件名字来获取图片的资源输入流
        InputStream pngInput = repositoryService.getResourceAsStream(deploymentId, pngName);
        // 同样的方式获取 bpmn 的输入流
        String bpmnName = processDefinition.getResourceName();
        InputStream bpmnInput = repositoryService.getResourceAsStream(deploymentId, bpmnName);

        // 6、构造 OutputStream 流
        File pngFile = new File("E:/evectionflow01.png");
        File bpmnFile = new File("E:/evectionflow01.bpmn");
        FileOutputStream pngOutStream = new FileOutputStream(pngFile);
        FileOutputStream bpmnOutStream = new FileOutputStream(bpmnFile);
        // 7、输入流，输出流的转换
        IOUtils.copy(pngInput, pngOutStream);
        IOUtils.copy(bpmnInput, bpmnOutStream);
        // 8、关闭流
        pngOutStream.close();
        bpmnOutStream.close();
        pngInput.close();
        bpmnInput.close();
    }

    /**
     * 查看历史信息
     */
    @Test
    public void testQueryHistoryInfo() {
        // 1、创建 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 HistoryService 历史管理类
        HistoryService historyService = processEngine.getHistoryService();
        // 3、获取 act_hi_actinst 表的查询对象
        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
        // 4、设置查询条件进行查询
        List<HistoricActivityInstance> historicActivityInstances = instanceQuery
                // .processInstanceId("5001") // 条件：根据 InstanceId 查询
                .processDefinitionId("myEvection:2:2504") // 条件：根据 InstanceId 查询
                .orderByHistoricActivityInstanceStartTime().asc() // 排序操作，根据开始时间 asc 升序排序
                .list(); // 查询所有内容列表
        // 循环数据
        for (HistoricActivityInstance instance : historicActivityInstances) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessDefinitionId());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("==========================");
        }
    }

}
