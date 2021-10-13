package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.cdi.KBase;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试Drools和Spring整合
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class DroolsSpringTest {
    @KBase("kbase")
    private KieBase kieBase;//注入KieBase对象

    @Test
    public void test1(){
        KieSession session = kieBase.newKieSession();
        session.fireAllRules();
        session.dispose();
    }

    @KSession("ksession")
    private KieSession kieSession;//不建议直接注入Session对象

    @Test
    public void test2(){
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
