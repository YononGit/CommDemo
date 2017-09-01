package com.yonon.demo.design.prodConsume;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class Producer implements Runnable{
    PenBox penBox = null;

    public Producer(PenBox penBox) {
        this.penBox = penBox;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 20; i++) {
            Pen pen = new Pen(i);
            penBox.Push(pen);
            System.out.println("生产了" + pen);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
