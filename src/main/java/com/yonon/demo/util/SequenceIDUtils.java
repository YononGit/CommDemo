package com.yonon.demo.util;

import com.yonon.demo.db.MySqlDriver;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jr-jiangyinghan on 2017-6-30.
 */
public class SequenceIDUtils {
    private static SimpleDateFormat sdf;
    private static Date date;
    private static volatile Integer index = 0;

    static {
        sdf = new SimpleDateFormat("yyyyMMdd");
    }

    public static synchronized void getID(String appSign, int count) throws InterruptedException {
        Thread thread = Thread.currentThread();

        for (int i = 0; i < count; i++) {
            System.out.println(thread.getName() + "--" + thread.getId());
            date = new Date();
            String seqID = sdf.format(date);
            System.out.println(appSign + seqID + ":" + getIndex() + ":" + getCount("SELECT COUNT(*) as rowCount from tb_user t"));
        }
    }

    private static synchronized Integer getIndex() {
        return index++;
    }

    private static synchronized Integer getCount(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = MySqlDriver.getRS(sql);
            if (resultSet == null) {
                System.out.println("not data..");
            }
            resultSet.next();
            return resultSet.getInt("rowCount");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
