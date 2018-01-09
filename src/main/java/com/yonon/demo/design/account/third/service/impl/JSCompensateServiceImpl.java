package com.yonon.demo.design.account.third.service.impl;

import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.enums.PartnerCodeEnum;
import com.yonon.demo.design.account.third.service.PartnerCompensateService;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */

public class JSCompensateServiceImpl implements PartnerCompensateService{
    @Override
    public MQDomain compensate(Object object) {
        System.out.println("js compensate");
        return null;
    }
}
