package com.yonon.demo.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by jr-jiangyinghan on 2017-7-10.
 */
public class UserFaceTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);


        while (in.hasNext()) {
            String input = in.next();
            if (input.equals("showDate")) {
                System.out.println("current date: " + sdf.format(new Date()));
            } else if (input.equals("setClock")) {
                System.out.println("input num of clock:");
                int num = in.nextInt();
                for (int i = 0; i < num; i++) {
                    System.out.println("set num: " + (i + 1) + " clock");
                    String id = sdf.format(new Date()) + "000" + i;
                    System.out.println("auto set id:" + id);
                    System.out.println("set clock name: ");
                    String name = in.next();
                    System.out.println("set dateClock(format.e.g 2017-07-10-15:60:30):");
                    boolean isMatch = false;
                    String dateSetting;
                    while (!isMatch) {
                        dateSetting = in.next();
                        String pattern = "\\d-\\d-\\d-\\d:\\d:\\d";
                        isMatch = Pattern.matches(pattern, dateSetting);
                        if (!isMatch)
                            System.out.println("date format error, retry");
                    }
                    System.out.println("it is repeat?");
                    String repeatStr = in.next();
                    boolean repeatResult = false;
                    boolean isRepeat = false;
                    while (!repeatResult) {
                        if ("Y".equals(repeatStr) || repeatStr.equals("y")) {
                            isRepeat = true;
                            repeatResult = true;
                            System.out.println("input ");
                        } else if (repeatStr.equals("N") || repeatStr.equals("n")) {
                            isRepeat = false;
                            repeatResult = true;
                        } else {
                            repeatResult = false;
                            System.out.println("repeat error ");
                        }
                    }
//                    MiniKitty miniKitty = new MiniKitty(id, name, dateSetting, "", isRepeat, repeats[]);
                }
            } else if (input.equals("exit")) {

            }

        }
    }
}
