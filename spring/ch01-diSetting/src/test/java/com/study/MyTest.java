package com.study;


import com.study.ba4.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test02() {
        //使用spring
        //1.使用spring配置文件的名称
        String config = "ba1\\applicationPathXml.xml";
        //2.创建spring容器对象，application
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获得对象
        Student student = (Student) ac.getBean("Student");
        System.out.println(student);
    }

    @Test
    public void test03() {
        //使用spring
        //1.使用spring配置文件的名称
        String config = "ba2\\applicationPathXml.xml";
        //2.创建spring容器对象，application
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获得对象
        Student student = (Student) ac.getBean("student");
        System.out.println(student);
    }

    @Test
    public void test04() {
        //使用spring
        //1.使用spring配置文件的名称
        String config = "ba3\\applicationPathXml.xml";
        //2.创建spring容器对象，application
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //获得对象
        Student student = (Student) ac.getBean("student");
        System.out.println(student);
    }
    @Test
    public void test05(){
        String config = "ba4\\total.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student student = (Student) ac.getBean("myStudent");
        System.out.println(student);

    }

}