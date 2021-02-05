package com.study;

import com.study.ba1.domain.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service("myAspect")
@Aspect
public class MyAspect {
   /**@AfterReturning(value = "execution(* *..SomeServiceImp.doSome(..))",
   returning = "res")
   public void doSome1(JoinPoint jp ,Object res ){
       System.out.println("原学生======"+res);
       System.out.println(jp.getSignature());

       Student student = (Student) res;
       student.setAge(100);
       student.setName("zhuangsan");
       System.out.println("提交事务");
   }**/
   @Around(value = "execution(* *..SomeServiceImp.doSome(..))")
   public Object doOther(ProceedingJoinPoint pj) throws Throwable {
       //Student student = (Student) pj.proceed();//相对于invoke;
       //student.setName("huang");
       Student student = new Student();
       return student;
   }
}
