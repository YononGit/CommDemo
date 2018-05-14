package com.yonon.demo.design.strategy;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-12-11.
 */
public class Price {
    //持有一个具体的策略对象
    private Strategy strategy;
    /**
     * 构造函数，传入一个具体的策略对象
     * @param strategy    具体的策略对象
     */
    public Price(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * 计算图书的价格
     * @param price    图书的原价
     * @return    计算出打折后的价格
     */
    public Double quote(Double price){
        return this.strategy.strategyInterface(price);
    }
}
