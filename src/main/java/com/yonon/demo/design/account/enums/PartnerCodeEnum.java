package com.yonon.demo.design.account.enums;

import org.springframework.util.StringUtils;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public enum PartnerCodeEnum {
    XFXJ("013", "THIRD_XFXJ", "幸福消金", "XFXJ"),
    JS("001", "THIRD_JS", "晋商", "JS");

    // 资金方编号
    private String code;
    // 资金方简称
    private String thirdCode;
    // 资金方名称
    private String name;
    // beanPrefix
    private String beanPrefix;

    PartnerCodeEnum(String code, String thirdCode, String name, String beanPrefix) {
        this.code = code;
        this.thirdCode = thirdCode;
        this.name = name;
        this.beanPrefix = beanPrefix;
    }

    public static PartnerCodeEnum getEnumByThirdCode(String thirdCode) {
        if (StringUtils.isEmpty(thirdCode)) {
            return null;
        }
        for (PartnerCodeEnum partnerCodeEnum : PartnerCodeEnum.values()) {
            if (partnerCodeEnum.getThirdCode().equals(thirdCode)) {
                return partnerCodeEnum;
            }
        }
        return null;
    }

    public String getBeanPrefix() {
        return beanPrefix;
    }

    public String getCode() {
        return code;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public String getName() {
        return name;
    }
}
