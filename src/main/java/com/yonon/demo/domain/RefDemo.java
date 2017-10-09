package com.yonon.demo.domain;

/**
 * Created by jr-jiangyinghan on 2017-9-26.
 */
public class RefDemo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    public RefDemo(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
