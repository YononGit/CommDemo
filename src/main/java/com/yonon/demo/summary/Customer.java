package com.yonon.demo.summary;

/**
 * Created by jr-jiangyinghan on 2017-7-7.
 */
public class Customer {
    private int id;
    private String name;
    private int currentFloor;
    private int targetFloor;

    public Customer(int id, String name, int currentFloor, int targetFloor) {
        this.id = id;
        this.name = name;
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
    }

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

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }
}
