package com.yonon.demo.design.account.enums;

import org.springframework.util.StringUtils;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public enum PartnerCodeEnum {
    XFXJ("013","XFXJ","幸福消金"),
    JS("001","JS","晋商");

    // 资金方编号
    private String code;
    // 资金方简称
    private String simpleName;
    // 资金方名称
    private String name;

    public String getCode() {
        return code;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getName() {
        return name;
    }

    PartnerCodeEnum(String code, String simpleName, String name) {
        this.code = code;
        this.simpleName = simpleName;
        this.name = name;
    }

    public static PartnerCodeEnum getEnumByCode(String code){
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        for(PartnerCodeEnum partnerCodeEnum: PartnerCodeEnum.values()){
            if(partnerCodeEnum.getCode().equals(code)){
                return partnerCodeEnum;
            }
        }
        return null;
    }
}
