package com.yonon.demo.run;

import com.yonon.demo.util.SequenceRedisID;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class RedisUtilGetID implements Runnable {

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
        int id = SequenceRedisID.getID();
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "get id: " + id);
    }
}
