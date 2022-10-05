package com.moon.drools.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.cdi.KBase;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 整合 Drools 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-09-30 21:28
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class DroolsSpringTest {

    @KBase("kbase")
    private KieBase kieBase; // 注入 KieBase 对象

    @KSession("ksession")
    private KieSession kieSession; // 不建议直接注入 KieSession 对象


    @Test
    public void test1() {
        KieSession session = kieBase.newKieSession();
        session.fireAllRules();
        session.dispose();
    }

    @Test
    public void test2() {
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
