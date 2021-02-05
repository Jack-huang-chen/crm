package com.study.dao;

import com.study.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStudent();
    int insertStudent(Student student);
}
