package com.yonon.demo.domain;

import java.io.Serializable;

/**
 * Created by jr-jiangyinghan on 2017-9-15.
 */
public class Student implements Serializable{

    private String id;
    private String name;
    private String sex;
    private int age;
    private String dept;

    public Student(String id, String name, String sex, int age, String dept) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", dept='" + dept + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
