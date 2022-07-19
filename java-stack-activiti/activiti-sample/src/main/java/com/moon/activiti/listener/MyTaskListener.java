package com.moon.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Activiti 自定义任务监听器，
 * 需要 org.activiti.engine.delegate.TaskListener 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-13 15:14
 * @description
 */
public class MyTaskListener implements TaskListener {

    /**
     * 流程启动时，会回调此方法
     *
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        // 判断当前的任务是“创建申请”并且是“create 事件”
        if ("创建申请".equals(delegateTask.getName()) &&
                "create".equals(delegateTask.getEventName())) {
            delegateTask.setAssignee("天锁斩月");
        }
    }

}
