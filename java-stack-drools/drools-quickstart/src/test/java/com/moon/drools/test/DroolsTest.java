package com.moon.drools.test;

import com.moon.drools.entity.ComparisonOperatorEntity;
import com.moon.drools.entity.Order;
import com.moon.drools.entity.Student;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Drools 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-09-13 22:33
 * @description
 */
public class DroolsTest {

    // 基础使用  --> 对应的规则文件是 bookDiscount.drl
    @Test
    public void test1() {
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
        System.out.println("没有激活规则前，优惠价格属性是：" + order.getRealPrice());

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
        System.out.println("优惠后价格：" + order.getRealPrice());
    }

    // 测试使用比较操作符  --> 对应的规则文件是 comparisonOperator.drl
    @Test
    public void test2() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 构造条件对象（Fact对象，事实对象），设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        ComparisonOperatorEntity fact = new ComparisonOperatorEntity();
        fact.setNames("李四");
        List<String> list = new ArrayList<>();
        list.add("张三2");
        fact.setList(list);

        // 将数据提供给规则引擎（放入工作内存中），规则引擎会根据提供的数据进行规则匹配
        session.insert(fact);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
    }

    // 测试执行指定规则  --> 对应的规则文件是 comparisonOperator.drl
    @Test
    public void test3() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 构造Fact对象（事实对象）
        ComparisonOperatorEntity fact = new ComparisonOperatorEntity();
        fact.setNames("李四");
        List<String> list = new ArrayList<>();
        list.add("张三2");
        fact.setList(list);
        // 将数据提供给规则引擎（放入工作内存中），规则引擎会根据提供的数据进行规则匹配
        session.insert(fact);

        // 使用框架提供的规则过滤器(RuleNameStartsWithAgendaFilter)执行指定某个名称的规则
        session.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_notcontains"));
        // 使用框架提供的规则过滤器(RuleNameStartsWithAgendaFilter)执行指定某个前缀的规则
        // session.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_"));

        // 关闭会话
        session.dispose();
    }

    // 测试 Drools 内置方法---update  --> 对应的规则文件是 method-update.drl
    @Test
    public void test4() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 构造Fact对象（事实对象）
        Student student = new Student();
        student.setAge(4);
        // 将数据提供给规则引擎（放入工作内存中），规则引擎会根据提供的数据进行规则匹配
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
    }

    // 测试 Drools 内置方法---insert  --> 对应的规则文件是 method-insert.drl
    @Test
    public void test5() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 构造Fact对象（事实对象）
        Student student = new Student();
        student.setAge(10);
        // 将数据提供给规则引擎（放入工作内存中），规则引擎会根据提供的数据进行规则匹配
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
    }

    // 测试 Drools 内置方法---retract  --> 对应的规则文件是 method-retract.drl
    @Test
    public void test6() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 构造Fact对象（事实对象）
        Student student = new Student();
        student.setAge(10);
        // 将数据提供给规则引擎（放入工作内存中），规则引擎会根据提供的数据进行规则匹配
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 enabled  --> 对应的规则文件是 attributes-enabled.drl
    @Test
    public void test7() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 salience  --> 对应的规则文件是 attributes-salience.drl
    @Test
    public void test8() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 no-loop  --> 对应的规则文件是 attributes-noloop.drl
    @Test
    public void test9() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();
        // 构造Fact对象（事实对象）
        Student student = new Student();
        student.setAge(50);
        // 将数据提供给规则引擎（放入工作内存中），规则引擎会根据提供的数据进行规则匹配
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 activation-group  --> 对应的规则文件是 attributes-activationgroup.drl
    @Test
    public void test10() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();
        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 agenda-group  --> 对应的规则文件是 attributes-agendagroup.drl
    @Test
    public void test11() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 设置焦点，对应agenda-group分组中的规则才可能被触发
        session.getAgenda().getAgendaGroup("agenda_group_1").setFocus();

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 auto-focus  --> 对应的规则文件是 attributes-autofocus.drl
    @Test
    public void test12() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();
        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 timer（实现方式1） --> 对应的规则文件是 attributes-timer.drl
    @Test
    public void test13() throws Exception {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        final KieSession session = kieContainer.newKieSession();

        new Thread(() -> {
            // 启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
            session.fireUntilHalt();
        }).start();

        Thread.sleep(10000);
        // 结束规则引擎
        session.halt();
        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 timer（实现方式2） --> 对应的规则文件是 attributes-timer-cron.drl
    @Test
    public void test14() throws Exception {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        final KieSession session = kieContainer.newKieSession();

        new Thread(() -> {
            // 启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
            session.fireUntilHalt();
        }).start();

        Thread.sleep(10000);
        // 结束规则引擎
        session.halt();
        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 date-effective --> 对应的规则文件是 attributes-dateeffective.drl
    @Test
    public void test15() {
        // 设置日期格式，设置环境变量名称固定为 drools.dateformat
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();
        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试规则属性 date-expires --> 对应的规则文件是 attributes-dateexpires.drl
    @Test
    public void test16() {
        // 设置日期格式，设置环境变量名称固定为 drools.dateformat
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();
        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试global 全局变量 --> 对应的规则文件是 global.drl
    @Test
    public void test17() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 设置全局变量，变量名称必须和规则文件中定义的变量名一致
        session.setGlobal("count", 5); // 包装对象的全局变量

        List<String> list = new ArrayList<>();
        list.add("N");
        session.setGlobal("globalList", list); // 集合类型的全局变量

        Student student = new Student();
        session.setGlobal("globalStudent", student);

        System.out.println("执行规则前全局变量globalList的size：" + list.size());
        System.out.println("执行规则前全局变量globalStudent的name属性：" + student.getName());
        System.out.println("--------------------------------------------------------");

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        System.out.println("--------------------------------------------------------");
        System.out.println("执行规则后全局变量globalList的size：" + list.size());
        System.out.println("执行规则后全局变量globalStudent的name属性：" + student.getName());

        // 关闭会话
        session.dispose();
    }

    // 测试 query 查询 --> 对应的规则文件是 query.drl
    @Test
    public void test18() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 创建多个对象，后面加入到working memory(工作内存)中，用于测试
        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(12);
        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(8);
        Student student3 = new Student();
        student3.setName("王五");
        student3.setAge(22);

        // 将对象插入Working Memory中
        session.insert(student1);
        session.insert(student2);
        session.insert(student3);

        // 调用规则文件中的查询（不带参数的查询）
        QueryResults results1 = session.getQueryResults("rule_query_1");
        int size = results1.size();
        System.out.println("size=" + size);
        for (QueryResultsRow row : results1) {
            Student student = (Student) row.get("$s"); // 与规则文件设置对象的变量名称一致
            System.out.println(student);
        }

        // 调用规则文件中的查询（带有参数的查询）
        QueryResults results2 = session.getQueryResults("rule_query_2", "王五");
        size = results2.size();
        System.out.println("size=" + size);
        for (QueryResultsRow row : results2) {
            Student student = (Student) row.get("$s");
            System.out.println(student);
        }

        // session.fireAllRules(); // 因为只是测试查询工作内存，不需要激活规则
        // 关闭会话
        session.dispose();
    }

    // 测试 function 函数 --> 对应的规则文件是 function.drl
    @Test
    public void test19() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        Student student = new Student();
        student.setName("MooNkirA");
        student.setAge(28);
        // 加入到工作内存中
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试 LHS 部分进阶复合值限制 in/not in --> 对应的规则文件是 lhs-in-notin.drl
    @Test
    public void test20() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        Student student = new Student();
        student.setName("Zero");
        // 加入到工作内存中
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试 LHS 部分进阶条件元素 eval --> 对应的规则文件是 lhs-eval.drl
    @Test
    public void test21() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();
        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试 LHS 部分进阶条件元素 not --> 对应的规则文件是 lhs-not.drl
    @Test
    public void test22() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        Student student = new Student();
        student.setAge(22);
        // 加入到工作内存中
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试 LHS 部分进阶条件元素 exists --> 对应的规则文件是 lhs-exists.drl
    @Test
    public void test23() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 加入两个对象到工作内存中
        session.insert(new Student());
        session.insert(new Student());

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试 LHS 部分进阶规则继承 extends --> 对应的规则文件是 lhs-extends.drl
    @Test
    public void test24() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        Student student = new Student();
        student.setAge(8);
        // 加入到工作内存中
        session.insert(student);

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    // 测试 RHS 部分进阶 --> 对应的规则文件是 rhs-drools.drl
    @Test
    public void test25() {
        // 获取 KieServices
        KieServices kieServices = KieServices.Factory.get();
        // 获得 KieContainer（容器）对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从 KieContainer（容器）对象中获取会话对象，用于和规则引擎交互
        KieSession session = kieContainer.newKieSession();

        // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

}
