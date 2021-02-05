package com.study.ba5;

public class School {
    private String name;
    private String palace;

    public School(String name, String palace) {
        this.name = name;
        this.palace = palace;
    }


    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", palace='" + palace + '\'' +
                '}';
    }
}
