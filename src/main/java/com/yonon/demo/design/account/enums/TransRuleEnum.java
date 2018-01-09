package com.yonon.demo.design.account.enums;

import org.springframework.util.StringUtils;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-04.
 */
public enum TransRuleEnum {
    NORMAL("normal", "正常"),
    ALLOT("allot", "按比例计算"),
    THIRD("third", "取第三方值");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    TransRuleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TransRuleEnum getEnumByCode(String code){
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        for(TransRuleEnum transRule: TransRuleEnum.values()){
            if(transRule.getCode().equals(code)){
                return transRule;
            }
        }
        return null;
    }

}
