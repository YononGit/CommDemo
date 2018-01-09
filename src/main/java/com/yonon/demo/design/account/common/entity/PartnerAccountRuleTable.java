package com.yonon.demo.design.account.common.entity;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-04.
 */
public class PartnerAccountRuleTable {
    private String partnerCode;
    private String transType;
    private String content;

    public PartnerAccountRuleTable(String partnerCode, String transType, String content) {
        this.partnerCode = partnerCode;
        this.transType = transType;
        this.content = content;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
