package com.yonon.demo.excel;

/**
 * Created by jr-jiangyinghan on 2017-10-9.
 */
enum Direction {
    D("D", "½è"),
    C("C", "´û");
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
    //1.1.0
    public void newAddFeature(){

    }
    // 1.5.0
    public void method150(){

    }
}
