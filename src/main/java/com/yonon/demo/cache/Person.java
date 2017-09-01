package com.yonon.demo.cache;

/**
 * Created by jr-jiangyinghan on 2017-8-31.
 */
public class Person {
    private String name;
    private int age;
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
