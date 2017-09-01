package com.yonon.demo.design.prodConsume;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class ProductAndConsume {
    public static void main(String[] args) {
        PenBox penBox = new PenBox();
        Producer producer = new Producer(penBox);
        Consumer consumer = new Consumer(penBox);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
