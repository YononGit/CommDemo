package com.yonon.demo.main;

import java.io.IOException;

/**
 * Created by jr-jiangyinghan on 2017-8-29.
 */
public class SysExc {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
