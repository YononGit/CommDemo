package com.yonon.demo.Construct;

/**
 * Created by jr-jiangyinghan on 2017-9-13.
 */
public class StaticMain {
    public StaticMain() {
        System.out.println("static main class construct");
    }

    public static String flag = "MASTER";

    public static String log(String stmp, String... strings) {
        String content = stmp;
        for (int i = 0; i < strings.length; i++) {
            content += (strings[i]);
        }
        System.out.println(content);
        return content;
    }
    public static String log4j(String stmp, String... strings) {

        String content = stmp;
        for (int i = 0; i < strings.length; i++) {
            content=content.replaceFirst("\\{\\}", strings[i]);
        }
        System.out.println(content);
        return content;
    }
}
