package com.yonon.demo.design.account.enums;

import org.springframework.util.StringUtils;

/**
 * @author: JiangYinghan
 * @Description: 根据TransRuleEnum 如果allot-int
 * @Date: Create on 2018-01-04.
 */
public enum TransFeeEnum {
    INT("int", "只取利息且判断是否分润"),
    OINT("oint", "只取罚息且判断是否分润"),
    INT_NOT_OINT("intNotOint", "利息判断是否分润,罚息不判断"),
    INT_AND_OINT("intAndOint","利息判断是否分润,罚息判断是否分润"),
    INT_ADD_OINT("intAddOint","利息加罚息作为利息且判断是否分润");
    /** NULL 走默认common方法(取利息和罚息且判断是否分润)**/
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    TransFeeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TransFeeEnum getEnumByCode(String code){
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        for(TransFeeEnum transFeeEnum: TransFeeEnum.values()){
            if(transFeeEnum.getCode().equals(code)){
                return transFeeEnum;
            }
        }
        return null;
    }
}
