package com.yonon.demo.design.factory;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-03-19.
 */
public abstract class Product {
    public void factoryMsg(){
        System.out.println("power by abstract");
    }

    public abstract  void color();
}
