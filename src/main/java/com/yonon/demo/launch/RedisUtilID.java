package com.yonon.demo.launch;

        import com.yonon.demo.run.RedisUtilGetID;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class RedisUtilID {
    public static void main(String[] args) throws  Exception{
        RedisUtilGetID getID;
        for(int i = 0; i < 1; i ++) {
            getID = new RedisUtilGetID();
            Thread t = new Thread(getID);
            t.setName("t_th_name_" + i);
            t.start();
        }
        Thread.sleep(60000);
    }
}
