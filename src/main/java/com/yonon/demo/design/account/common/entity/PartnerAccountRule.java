package com.yonon.demo.design.account.common.entity;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-04.
 */
public class PartnerAccountRule {
    private String partnerCode;
    private String transType;
    private String transCodeAC;
    private String transCodeDB;
    private String transCodeCP;
    private String transCodeRP;
    private String transCodeNAC;

    public PartnerAccountRule(String partnerCode, String transType, String transCodeAC, String transCodeDB, String transCodeCP, String transCodeRP, String transCodeNAC) {
        this.partnerCode = partnerCode;
        this.transType = transType;
        this.transCodeAC = transCodeAC;
        this.transCodeDB = transCodeDB;
        this.transCodeCP = transCodeCP;
        this.transCodeRP = transCodeRP;
        this.transCodeNAC = transCodeNAC;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Override
    public String toString() {
        return "PartnerAccountRule{" +
                "partnerCode='" + partnerCode + '\'' +
                ", transType='" + transType + '\'' +
                ", transCodeAC='" + transCodeAC + '\'' +
                ", transCodeDB='" + transCodeDB + '\'' +
                ", transCodeCP='" + transCodeCP + '\'' +
                ", transCodeRP='" + transCodeRP + '\'' +
                ", transCodeNAC='" + transCodeNAC + '\'' +
                '}';
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getTransCodeAC() {
        return transCodeAC;
    }

    public void setTransCodeAC(String transCodeAC) {
        this.transCodeAC = transCodeAC;
    }

    public String getTransCodeDB() {
        return transCodeDB;
    }

    public void setTransCodeDB(String transCodeDB) {
        this.transCodeDB = transCodeDB;
    }

    public String getTransCodeCP() {
        return transCodeCP;
    }

    public void setTransCodeCP(String transCodeCP) {
        this.transCodeCP = transCodeCP;
    }

    public String getTransCodeRP() {
        return transCodeRP;
    }

    public void setTransCodeRP(String transCodeRP) {
        this.transCodeRP = transCodeRP;
    }

    public String getTransCodeNAC() {
        return transCodeNAC;
    }

    public void setTransCodeNAC(String transCodeNAC) {
        this.transCodeNAC = transCodeNAC;
    }
}
