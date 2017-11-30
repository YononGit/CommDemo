package com.yonon.demo.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-11-23.
 */
public class DayUtilsMain {
    public static void main(String[] args) {
        try {
            // 1、上线日的次日对晋商历史数据做第一次代偿
            // 2、每月20日左右代偿，需要根据是否工作日判断，仅在工作日触发代偿，若为20号，到最近工作日触发。
            // 3、晋商逾期60天以上未代偿数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String firstTriggerDateStr = "2018-02-20";
            isWorkingDay(firstTriggerDateStr, 20, sdf.parse(firstTriggerDateStr));
        } catch (Exception ex) {

        }
    }

    /**
     * 日期设置为可配置的 system_param
     * 判断当前时间是不是某一天 且是不是工作日 如果不是工作日向后判断
     */
    private static Boolean isWorkingDay(String firstTriggerDateStr, Integer compensateDateInt, Date currentDate) {
        // 判断时间是不是2017-12-08
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date firstTriggerDate = sdf.parse(firstTriggerDateStr);

            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            Integer day = cal.get(Calendar.DATE);

            // 判断时间是不是第一次上线日的次日2017-12-07
            if (sdf.format(firstTriggerDate).equals(sdf.format(new Date()))) {
                System.out.println("符合要求 is equals");
                return true;
            } else if (day.equals(compensateDateInt) || day.equals(compensateDateInt + 1) || day.equals(compensateDateInt + 2)) {
                //判断是不是20号
                System.out.println("day is " + compensateDateInt);
                boolean isNotWorkDay = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
                boolean isMonDay = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
                System.out.println("isNotWorkDay:" + isNotWorkDay);
                if (day.equals(compensateDateInt) && !isNotWorkDay) {
                    // 判断是不是工作日
                    System.out.println("20 is working day");
                    return true;
                } else if (day.equals(compensateDateInt + 1) && !isNotWorkDay && isMonDay) {
                    System.out.println("20 is not working day,but 21 is month");
                    return true;
                } else if (day.equals(compensateDateInt + 2) && !isNotWorkDay && isMonDay) {
                    System.out.println("20 21 is not working day,but 22 is monday");
                    return true;
                }else {
                    System.out.println("is not useful day:" + day);
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

}
