package com.yonon.demo.Construct;

/**
 * Created by jr-jiangyinghan on 2017-9-13.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.printf("Demo call static main class:" + StaticMain.flag);
        System.out.println("running");

        StaticMain.log("log:","arg1","arg2");

        StaticMain.log4j("log:{},{}","1","2");
    }
}
