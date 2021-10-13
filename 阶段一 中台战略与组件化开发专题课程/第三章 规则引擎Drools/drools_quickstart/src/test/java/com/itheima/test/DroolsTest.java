package com.itheima.test;

import com.itheima.drools.entity.ComparisonOperatorEntity;
import com.itheima.drools.entity.Order;
import com.itheima.drools.entity.Student;
import com.itheima.drools.service.UserService;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.List;

public class DroolsTest {
    @Test
    public void test1(){
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //Fact对象，事实对象
        Order order = new Order();
        order.setOriginalPrice(110d);
        //将Order对象插入到工作内存中
        session.insert(order);

        System.out.println("----优惠后价格："+order.getRealPrice());

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        //关闭会话
        session.dispose();
        System.out.println("优惠后价格："+order.getRealPrice());
    }

    //测试比较操作符
    @Test
    public void test2(){
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //Fact对象，事实对象
        ComparisonOperatorEntity fact = new ComparisonOperatorEntity();
//        fact.setNames("李四");
//        fact.setNames("张三");
        fact.setNames("王海红");

        List<String> list = new ArrayList<String>();
//        list.add("张三");
//        list.add("李四");
        list.add("王海红");

        fact.setList(list);

        session.insert(fact);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        //关闭会话
        session.dispose();
    }

    //测试执行指定规则
    @Test
    public void test3(){
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //Fact对象，事实对象
        ComparisonOperatorEntity fact = new ComparisonOperatorEntity();
        fact.setNames("李四");

        List<String> list = new ArrayList<String>();
        list.add("张三2");
        //list.add("李四");

        fact.setList(list);

        session.insert(fact);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        //使用框架提供的规则过滤器执行指定规则
        //session.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_notcontains"));
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_"));
        //关闭会话
        session.dispose();
    }

    //测试Drools内置方法---update
    @Test
    public void test4(){
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        Student student = new Student();
        student.setAge(10);

        session.insert(student);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        //关闭会话
        session.dispose();
    }

    //测试Drools内置方法---insert
    @Test
    public void test5(){
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        Student student = new Student();
        student.setAge(50);

        session.insert(student);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        //关闭会话
        session.dispose();
    }

    //测试规则属性agenda-group属性
    @Test
    public void test6(){
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //指定组获得焦点
        session.getAgenda().getAgendaGroup("agenda_group_1").setFocus();

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        //关闭会话
        session.dispose();
    }

    //测试规则属性timer属性
    @Test
    public void test7() throws Exception{
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        final KieSession session = kieContainer.newKieSession();

        new Thread(new Runnable() {
            public void run() {
                //启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
                session.fireUntilHalt();
            }
        }).start();

        Thread.sleep(10000);
        //结束规则引擎
        session.halt();

        //关闭会话
        session.dispose();
    }

    //测试规则属性date-effective属性
    @Test
    public void test8(){
        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
//        session.fireAllRules(new RuleNameEqualsAgendaFilter("rule_dateeffective_1"));

        //关闭会话
        session.dispose();
    }

    //测试全局变量
    @Test
    public void test9(){
        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //设置全局变量，变量名称必须和规则文件中定义的变量名一致
        session.setGlobal("count",5);

        List<String> list = new ArrayList<String>();
        list.add("itheima");
        session.setGlobal("gList",list);

        session.setGlobal("userService",new UserService());

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_global"));

        System.out.println("在Java程序中全局变量gList的size为：" + list.size());
        //关闭会话
        session.dispose();
    }

    //测试Query查询
    @Test
    public void test10(){
        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //设置全局变量，变量名称必须和规则文件中定义的变量名一致
        session.setGlobal("count",5);

        List<String> list = new ArrayList<String>();
        list.add("itheima");
        session.setGlobal("gList",list);

        session.setGlobal("userService",new UserService());
        System.out.println("==================");

        Student s1 = new Student();
        s1.setAge(50);
        s1.setName("张三");
        Student s2 = new Student();
        s2.setAge(50);
        s2.setName("张三");
        session.insert(s1);
        session.insert(s2);

        //根据名称调用规则文件中定义的查询
        QueryResults results1 = session.getQueryResults("query_1");
        int size = results1.size();
        System.out.println("符合条件的Fact对象个数："+size);
        for (QueryResultsRow row : results1) {
            Student s= (Student) row.get("$s");
            System.out.println(s);
        }

        QueryResults results2 = session.getQueryResults("query_2","张三");
        int size2 = results2.size();
        System.out.println("符合条件的Fact对象个数："+size2);
        for (QueryResultsRow row : results2) {
            Student s= (Student) row.get("$s");
            System.out.println(s);
        }

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        //session.fireAllRules();

        //关闭会话
        session.dispose();
    }

    //测试function函数
    @Test
    public void test11(){
        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //设置全局变量，变量名称必须和规则文件中定义的变量名一致
        session.setGlobal("count",5);

        List<String> list = new ArrayList<String>();
        list.add("itheima");
        session.setGlobal("gList",list);

        session.setGlobal("userService",new UserService());

        Student s1 = new Student();
        s1.setAge(61);
        s1.setName("张三");
        session.insert(s1);


        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();

        //关闭会话
        session.dispose();
    }

    //测试LHS部分in/not in eval not exists关键字
    @Test
    public void test12(){
        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //设置全局变量，变量名称必须和规则文件中定义的变量名一致
        session.setGlobal("count",5);

        List<String> list = new ArrayList<String>();
        list.add("itheima");
        session.setGlobal("gList",list);

        session.setGlobal("userService",new UserService());

//        Student s1 = new Student();
//        s1.setName("test");
//        session.insert(s1);
        Student s2 = new Student();
        s2.setAge(15);
        session.insert(s2);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_lhs"));

        //关闭会话
        session.dispose();
    }

    //测试RHS部分drools对象的方法
    @Test
    public void test13(){
        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();

        //设置全局变量，变量名称必须和规则文件中定义的变量名一致
        session.setGlobal("count",5);

        List<String> list = new ArrayList<String>();
        list.add("itheima");
        session.setGlobal("gList",list);

        session.setGlobal("userService",new UserService());

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_rhs"));

        //关闭会话
        session.dispose();
    }
}
