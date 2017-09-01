package com.yonon.demo.simple;

/**
 * Created by jr-jiangyinghan on 2017-7-3.
 */
public class SimpleMain {
    public static void main(String[] args) {
        String sequence = "1";
        String sequenceStr = "";
        sequenceStr = "000000" + sequence;
        sequenceStr = sequenceStr.substring(sequenceStr.length() - 6, sequenceStr.length());
        System.out.println(sequenceStr);

    }
}
