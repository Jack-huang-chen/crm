package com.study.service;

import com.study.domain.Student;

import java.util.List;

public interface StudentService {
    public int insertStudent(Student student);
    public List<Student> selectStudent();
}
