package com.yonon.demo.design.account.common.service;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.common.entity.PartnerAccountRule;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.account.enums.TransFeeEnum;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-02.
 */
public interface TransAccountBaseService {
    MQDomain genMQDomain(TransCodeEnum transCodeEnum, Loan loan, PartnerAccountRule partnerAccountRule, TransFeeEnum transFeeEnum);
}
