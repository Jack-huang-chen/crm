package com.study;

import com.study.service.SomeService;
import com.study.service.impl.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void Test01(){

        SomeService someService = new SomeServiceImpl();
        someService.doSome();
    }
    @Test
    public void test02(){
        //使用spring
        //1.使用spring配置文件的名称
        String config = "beans.xml";
        //2.创建spring容器对象，application
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获得对象
        SomeService someService = (SomeService) ac.getBean("SomeServiceImpl");//这里甜id
        someService.doSome();
    }
}
