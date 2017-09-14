package com.yonon.demo.launch;

import com.yonon.demo.redis.DBUtils;

/**
 * Created by jr-jiangyinghan on 2017-9-14.
 */
public class StressDB {
    public static void main(String[] args) {

        DBUtils.getConnect();
    }
}
