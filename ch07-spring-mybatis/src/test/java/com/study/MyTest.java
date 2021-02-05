package com.study;

import com.study.domain.Student;
import com.study.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class MyTest {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        StudentService studentService = (StudentService) ac.getBean("studentServiceImp");
        List<Student> studentList = studentService.selectStudent();
        for (Student student:studentList
             ) {
            System.out.println(student);
        }
    }
}
