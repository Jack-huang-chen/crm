package com.study.domain;

import org.springframework.stereotype.Component;

@Component("student")
public class Student {
    int id;
    String name;
    int no;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", no=" + no +
                '}';
    }
}
