package com.moon.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;

/**
 * 测试使用 Activiti 默认方式生成相关的数据库表
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-24 18:19
 * @description
 */
public class CreateTableTest {

    // 测试使用程序生成 Activiti 数据库表，使用默认的方式创建 ProcessEngine 对象
    @Test
    public void test01() {
        /*
         * 需要使用 avtiviti 提供的工具类 ProcessEngines 的 getDefaultProcessEngine 方法
         * 会默认从 resources 下读取名字为 actviti.cfg.xml 的文件。
         * 创建 ProcessEngine 对象同时会创建相应的数据表
         */
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }

    // 测试使用程序生成 Activiti 数据库表，使用自定义方式创建 ProcessEngine 对象
    @Test
    public void test02() {
        /*
         * 通过 ProcessEngineConfiguration 类的静态方法 createProcessEngineConfigurationFromResource
         * 自定义创建 ProcessEngineConfiguration 实例，方法参数分别指定配置文件名称与bean实例的名称
         * 即意味着 activiti 的配置文件名称可以自定义，创建 processEngineConfiguration 实例的id也可以自定义
         */
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.
                createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");

        // 获取流程引擎对象 ProcessEngine
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);
    }

}
