package com.yonon.demo.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class SimpleMain {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMain.class);

    public static void main(String[] args) throws Exception {

        String start = "10:25:13";
        String end = "10:27:00";
        isRangeTime(start, end, new Date());
    }

    /**
     * 判断当前时间点是否包含在指定时间段中
     *
     * @param start 起始时间
     * @param end   截止时间
     * @return true 为时间在指定范围内
     **/
    private static boolean isRangeTime(String start, String end, Date nowDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String dateStr = sdf.format(nowDate);
            System.out.println(dateStr + " range -> " + start + " - " + end);

            Date startTime = sdf.parse(start);
            Date endTime = sdf.parse(end);
            Date targetTime = sdf.parse(dateStr);
            long startLongTime = startTime.getTime();
            long endLongTime = endTime.getTime();
            long targetLongTime = targetTime.getTime();

            if (endLongTime > startLongTime) {
                if (startLongTime <= targetLongTime && endLongTime >= targetLongTime) {
                    return true;
                } else {
                    return false;
                }
            } else {
                logger.info("时间范围不正确！start: {},end: {}", start, end);
                return false;
            }
        } catch (Exception ex) {
            // 时间转换异常
            logger.info("时间判断异常start: {},end: {}", start, end, ex);
        }
        return false;
    }

    public static void mainDemo() {
        String sequence = "1";
        String sequenceStr = "";
        sequenceStr = "000000" + sequence;
        sequenceStr = sequenceStr.substring(sequenceStr.length() - 6, sequenceStr.length());
        System.out.println(sequenceStr);

    }
}
