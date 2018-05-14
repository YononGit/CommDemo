package com.yonon.demo.design.strategy;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-12-11.
 */
public class ConcreteStrategyC implements Strategy {
    /**
     * 策略方法
     */
    @Override
    public Double strategyInterface(Double price) {
        System.out.println(price);
        return price * 0.6;
    }
}
