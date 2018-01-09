package com.yonon.demo.design.account.common.service.impl;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.common.entity.PartnerAccountRule;
import com.yonon.demo.design.account.common.service.TransAccountBaseService;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.account.enums.TransFeeEnum;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-02.
 */
public class DBServiceImpl implements TransAccountBaseService {
    @Override
    public MQDomain genMQDomain(TransCodeEnum transCodeEnum, Loan object, PartnerAccountRule partnerAccountRule, TransFeeEnum transFeeEnum) {
        System.out.println("DB..");
        return null;
    }
}
