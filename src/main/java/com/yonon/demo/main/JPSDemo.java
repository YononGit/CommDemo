package com.yonon.demo.main;

import java.util.Scanner;

/**
 * Created by jr-jiangyinghan on 2017-8-29.
 */
public class JPSDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String param = in.next();
            if("exit".equals(param))
                break;
            System.out.println("print: " + param);
        }
    }
}
