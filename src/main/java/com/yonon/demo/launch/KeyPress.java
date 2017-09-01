package com.yonon.demo.launch;

import java.util.Scanner;

/**
 * Created by jr-jiangyinghan on 2017-7-6.
 */
public class KeyPress {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String press = in.next();
            StringBuffer sb = new StringBuffer();
            Keys key = Keys.toKeys(press);
            switch (key) {
                case A:

                    break;
                case B:

                    break;
                case C:

                    break;
                case D:

                    break;
                case E:

                    break;
                case F:

                    break;
                case G:

                    break;
                case W:

                    break;
                default:
                    break;
            }
        }
    }
}

enum Keys {
    A("A", "A"), B("B", "B"), C("C", "C"), D("D", "D"), E("E", "E"), F("F", "F"), G("G", "G"), W("W", "W"), S("S", "S");

    private String code;
    private String desc;

    Keys(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public static Keys toKeys(String code) {
        for (Keys key : Keys.values()) {
            if (key.getCode().equals(code))
                return key;
        }
        return null;
    }
}