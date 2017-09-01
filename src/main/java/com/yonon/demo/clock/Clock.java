package com.yonon.demo.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by jr-jiangyinghan on 2017-7-10.
 */
public class Clock {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String dateStr = in.next();
//            dateStr = "2017-07-10-15:60:30";
            Date setDate = new Date();
            try {
                setDate = sdf.parse(dateStr);
            } catch (Exception e) {
                System.out.println("time input error!");
            }
            System.out.println(sdf.format(setDate));

            TimeBreath tb = new TimeBreath();
            tb.setMemDate(setDate);
            Thread t1 = new Thread(tb);
            t1.start();
        }
    }
}
