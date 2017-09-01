package com.yonon.demo.summary;

/**
 * Created by jr-jiangyinghan on 2017-7-7.
 */
public class Elevator {
    private int id;
    private int top;
    private int base;
    private int current;
    private int direction;
    private int next;
    public Elevator(int id, int top, int base, int current, int direction,int next) {
        this.id = id;
        this.top = top;
        this.base = base;
        this.current = current;
        this.direction = direction;
        this.next = next;
        System.out.println("elevator:" + id + " set ok!  work at : " + base + " <*> " + top + " current:" + current + " direction: " + direction);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }
}

