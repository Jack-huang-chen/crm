package com.study.controllers;


import com.study.domain.Student;
import com.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/addStudent.do",method = RequestMethod.POST)
    public ModelAndView insertStudent(Student student){
        ModelAndView ma = new ModelAndView();
        String msg = "注册失败";
        int count = studentService.insertStudent(student);
        if (count == 1){
            msg = "注册成功";
        }
        ma.addObject("msg",msg);
        ma.setViewName("result");
        return ma;
    }
    @RequestMapping(value = "selectStudent.do",produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<Student> selectStudent(){
        List<Student> studentList = studentService.selectStudent();
        return studentList;
    }

}
