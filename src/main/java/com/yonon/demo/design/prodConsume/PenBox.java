package com.yonon.demo.design.prodConsume;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class PenBox {
    int index = 0;
    final int size = 6;
    Pen[] pens = new Pen[size];

    public synchronized void Push(Pen pen) {
        while (index == pens.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.notify();
        pens[index] = pen;
        index++;
    }

    public synchronized Pen Pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        return pens[index];
    }
}
