package com.study.service.imp;

import com.study.dao.StudentDao;
import com.study.domain.Student;
import com.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;



    @Override
    public List<Student> selectStudent() {
        List<Student> studentList = studentDao.selectStudent();
        return studentList;
    }

    @Override
    public int insertStudent(Student student) {
        int count = studentDao.insertStudent(student);
        return count;
    }
}
