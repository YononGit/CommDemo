package com.yonon.demo.design.account.third.service;

import com.yonon.demo.design.account.common.entity.MQDomain;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public interface TransCompensateService {
    MQDomain transCompensate(String partnerCode, Object object);
}
