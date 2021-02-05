package com.study.dao;

import com.study.domain.Student;

import java.util.List;

public interface StudentDao {
    public int insertStudent(Student student);
    public List<Student> selectStudent();
}
