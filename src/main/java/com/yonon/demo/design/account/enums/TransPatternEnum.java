package com.yonon.demo.design.account.enums;

import org.springframework.util.StringUtils;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-15.
 */
public enum TransPatternEnum {
    CASH("cash","00","随借随还"),
    TERM("term","03","等额本息");

    private String pattern;
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getPattern() {
        return pattern;
    }

    public String getName() {
        return name;
    }

    TransPatternEnum(String pattern, String code, String name) {
        this.code = code;
        this.pattern = pattern;
        this.name = name;
    }

    public static TransPatternEnum getEnumByCode(String code){
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        for(TransPatternEnum transPattern: TransPatternEnum.values()){
            if(transPattern.getCode().equals(code)){
                return transPattern;
            }
        }
        return null;
    }
}
