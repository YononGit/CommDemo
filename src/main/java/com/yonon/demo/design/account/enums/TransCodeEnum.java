package com.yonon.demo.design.account.enums;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-02.
 */
public enum TransCodeEnum {
    DB("DB", "放款"),
    AC("AC", "结计"),
    CP("CP", "逾期代偿"),
    PCP("CP","提前代偿");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    TransCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
