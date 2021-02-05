package com.study.ba3;

public class Student {
    private int age;
    private String name;
    private School school;

    public Student() {
    }

    public Student(int age, String name, School school) {
        this.age = age;
        this.name = name;
        this.school = school;
    }


    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", school=" + school +
                '}';
    }
}
