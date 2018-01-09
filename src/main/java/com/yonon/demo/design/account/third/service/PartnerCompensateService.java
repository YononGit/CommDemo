package com.yonon.demo.design.account.third.service;

import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.enums.PartnerCodeEnum;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public interface PartnerCompensateService {
    MQDomain compensate(Object object);
}
