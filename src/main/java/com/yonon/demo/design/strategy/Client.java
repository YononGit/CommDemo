package com.yonon.demo.design.strategy;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-12-11.
 */
public class Client {
    public static void main(String[] args) {
        //选择并创建需要使用的策略对象
        Strategy strategy = new ConcreteStrategyC();
        //创建环境
        Price price = new Price(strategy);
        //计算价格
        double quote = price.quote(300.00);
        System.out.println("图书的最终价格为：" + quote);
    }
}
