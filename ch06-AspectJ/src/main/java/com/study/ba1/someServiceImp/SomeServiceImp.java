package com.study.ba1.someServiceImp;

import com.study.ba1.SomeService;
import com.study.ba1.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("someService")
public class SomeServiceImp implements SomeService {
    @Autowired
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }


    @Override
    public Student doSome() {
        System.out.println("dosome方法执行");
        return student;
    }
}
