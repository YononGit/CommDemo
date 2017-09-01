package com.yonon.demo.util;

import com.mysql.jdbc.StringUtils;
import com.yonon.demo.redis.RedisUtils;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class SequenceRedisID {
    private static String lock;
    private final static String SEQ_ID = "seq_ID";
    private static int index = 0;
    public synchronized static int getID() {
        lock = RedisUtils.getRedisData(SEQ_ID);
        System.out.println("seq_ID value: " + lock);
        if (StringUtils.isNullOrEmpty(lock) || "unLock".equals(lock)) {
            System.out.println("run...");
            RedisUtils.setRedisData(SEQ_ID,"lock");
            return (index ++);
        } else {
            System.out.println("stop...");
            RedisUtils.setRedisData(SEQ_ID,"unLock");
        }
        return 0;
    }

}
