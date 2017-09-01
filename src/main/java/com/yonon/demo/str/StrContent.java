package com.yonon.demo.str;

/**
 * Created by jr-jiangyinghan on 2017-7-12.
 */
public class StrContent {
    private static String[] strs = {
            "A", "B", "C"
    };

    public static void main(String[] args) {
        String contentA = "A";
        System.out.println(containThirdCode(contentA));
        String contentB = "B";
        System.out.println(containThirdCode(contentB));
        String contentC = "C";
        System.out.println(containThirdCode(contentC));
        String contentD = "D";
        System.out.println(containThirdCode(contentD));

    }

    private static boolean containThirdCode(String thirdCode) {
        String targetThirdCodes[] = {"A", "B", "C"};
        for (String targetThirdCode : targetThirdCodes) {
            if (targetThirdCode.equals(thirdCode)) {
                return true;
            }
        }
        return false;
    }


}
