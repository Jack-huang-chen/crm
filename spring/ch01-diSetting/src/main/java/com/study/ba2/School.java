package com.study.ba2;

public class School {
    private String name;
    private String palace;

    public void setName(String name) {
        this.name = name;
    }

    public void setPalace(String palace) {
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
