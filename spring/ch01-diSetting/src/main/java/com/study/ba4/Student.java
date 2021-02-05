package com.study.ba4;

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

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(School school) {
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
