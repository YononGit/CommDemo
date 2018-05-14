package com.yonon.demo.design.account.common.entity;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public class Loan {
    private String id;
    private String third;
    private String name;
    private String rpyType;

    public Loan(String id, String third, String name, String rpyType) {
        this.id = id;
        this.third = third;
        this.name = name;
        this.rpyType = rpyType;
    }

    public String getRpyType() {
        return rpyType;
    }

    public void setRpyType(String rpyType) {
        this.rpyType = rpyType;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
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
}