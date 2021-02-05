package com.study;

import com.study.ba1.SomeService;
import com.study.ba1.domain.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void Test01(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        SomeService someService = (SomeService) ac.getBean("someService");

        someService.doSome();
    }
    @Test
    public void test02(){
        String a = "abc";

        System.out.println(a);
    }
}
