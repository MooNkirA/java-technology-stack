package com.moon.drools.test;

import com.moon.drools.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Drools 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-09-13 22:33
 * @description
 */
public class DroolsTest {

    @Test
    public void test1(){
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 构造订单对象（Fact对象，事实对象），设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        Order order = new Order();
        order.setOriginalPrice(500d);

        // 将数据提供给规则引擎（放入工作内存中），规则引擎会根据提供的数据进行规则匹配
        session.insert(order);

        System.out.println("----优惠后价格："+order.getRealPrice());

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
        System.out.println("优惠后价格："+order.getRealPrice());
    }

}
