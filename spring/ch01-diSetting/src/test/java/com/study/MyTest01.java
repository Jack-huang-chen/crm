package com.study;


import com.study.ba4.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest01 {

    @Test
    public void test02() {
        //使用spring
        //1.使用spring配置文件的名称
        String config = "ba5\\total.xml";
        //2.创建spring容器对象，application
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获得对象
        Student student = (Student) ac.getBean("student");
        System.out.println(student);
    }


}