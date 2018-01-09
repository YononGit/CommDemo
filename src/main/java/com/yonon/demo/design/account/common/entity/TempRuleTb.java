package com.yonon.demo.design.account.common.entity;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-09.
 */
public class TempRuleTb {
    private int id;
    private String partnerCode;
    private String custSeg;
    private String rpyType;
    private String content;
    private String produceCode;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    private String contentName;

    public String getProduceCode() {
        return produceCode;
    }

    public void setProduceCode(String produceCode) {
        this.produceCode = produceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getCustSeg() {
        return custSeg;
    }

    public void setCustSeg(String custSeg) {
        this.custSeg = custSeg;
    }

    public String getRpyType() {
        return rpyType;
    }

    public void setRpyType(String rpyType) {
        this.rpyType = rpyType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
