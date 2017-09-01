package com.yonon.demo.clock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jr-jiangyinghan on 2017-7-11.
 */
public class SystemClock implements Runnable {
    private boolean isShow = true;

    public boolean isShow() {
        System.out.println("get current status: " + isShow);
        return isShow;
    }

    public void setShow(boolean show) {
        System.out.println("set show status: " + show);
        this.isShow = show;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
//            System.out.println("system clock thread-run");
            if (isShow()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("current date: " + sdf.format(new Date()));
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
