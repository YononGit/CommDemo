package com.yonon.demo.thread;

import com.yonon.demo.redis.DBUtils;

/**
 * Created by jr-jiangyinghan on 2017-7-6.
 */
public class DBHeart implements Runnable {
    @Override
    public void run(){
        DBUtils.getConnect();
    }
}
