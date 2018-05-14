package com.yonon.demo.design.factory;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-03-19.
 */
public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> classArg);
}
