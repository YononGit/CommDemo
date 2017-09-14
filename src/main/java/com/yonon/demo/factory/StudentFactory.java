package com.yonon.demo.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.*;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by jr-jiangyinghan on 2017-9-14.
 */
public class StudentFactory {
    /**
     * The FieldPosition.
     */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /**
     * This Format for format the data to special format.
     */
    private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");

    /**
     * This Format for format the number to special format.
     */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");

    /**
     * This int is the sequence number ,the default value is 0.
     */
    private static int seq = 0;

    private static final int MAX = 9999;

    private static final Logger logger = LoggerFactory.getLogger(StudentFactory.class);

    public static String generateSequenceNo() {
        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }

        System.out.println("id:" + sb.toString());
        return sb.toString();
    }
    public static String genRandName() {
        String name = "";
        for (int i = 0; i < 6; i++) {
            int max = 122;
            int min = 97;
            int a = new Random().nextInt(max)%(max-min+1) + min;// 97-122
            name += String.valueOf((char) a);
        }
        return name;
    }

    //
    public static String getSex(int index) {
        String[] sexs = {"BOY", "GIRL"};
        int stmp = index % 2;
        return sexs[stmp];
    }

    public static double process(int count, int current) {
        double countProc = Double.valueOf(String.valueOf(count));
        double currentProc = Double.valueOf(String.valueOf(current));
        double proc = currentProc / countProc * 100;
        logger.info("process: " + proc + " %");
        return proc;
    }


    public static void main(String[] args) {
        process(100,6);
    }
}
