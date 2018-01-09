package com.yonon.demo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jr-jiangyinghan on 2017-8-22.
 */
public class mian1 {

    private static Logger logger = LoggerFactory.getLogger(mian1.class);

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        tx();
        System.out.println(a + b);
    }

    private static void tx() {
        try {
            String date = "2017-1-12";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date cdate = sdf.parse("2017-01");
            System.out.println(cdate.equals(sdf.format(cdate)));
        } catch (Exception ex) {
            logger.error("error: ", ex);
        }
    }


}
