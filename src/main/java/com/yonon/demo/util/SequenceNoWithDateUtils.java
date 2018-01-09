package com.yonon.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-12-14.
 */
public class SequenceNoWithDateUtils {
    public static void main(String[] args) {
        for (int count = 1; count < 999999; count++) {
            getSeqNo("HM", count);
        }
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static String dateStr = sdf.format(new Date());

    private static String getSeqNo(String prefix, int count) {
        return prefix.concat(dateStr.concat(String.format("%012d", count)));
    }
}
