package com.yonon.demo.design.factory;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-03-19.
 */
public class FactoryMain {
    public static void main(String[] args) {
        ConcreateCreator factory = new ConcreateCreator();
        Product redPen = factory.createProduct(RedPen.class);
        redPen.color();
        redPen.factoryMsg();
        Product yellowPen = factory.createProduct(YellowPen.class);
        yellowPen.color();
        yellowPen.factoryMsg();
    }
}
