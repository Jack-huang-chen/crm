package com.study.service.imp;

import com.study.dao.StudentDao;
import com.study.domain.Student;
import com.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class StudentServiceImp implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int insertStudent(Student student) {

        int count = 0;
        count = studentDao.insertStudent(student);
        return count;
    }

    @Override
    public List<Student> selectStudent() {
        List<Student> studentList = studentDao.selectStudent();
        return studentList;
    }
}
