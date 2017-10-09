package com.yonon.demo.excel;

/**
 * Created by jr-jiangyinghan on 2017-10-9.
 */
enum Direction {
    D("D", "借"),
    C("C", "贷");
    private String code;
    private String name;

    Direction(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getCodeByName(String name) {
        for (Direction direction : Direction.values()) {
            if (direction.getName().equals(name)) {
                return direction.getCode();
            }
        }
        return null;
    }
}
