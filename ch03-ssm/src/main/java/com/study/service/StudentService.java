package com.study.service;

import com.study.dao.StudentDao;
import com.study.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> selectStudent();
    int insertStudent(Student student);
}
