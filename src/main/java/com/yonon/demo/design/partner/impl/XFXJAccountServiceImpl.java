package com.yonon.demo.design.partner.impl;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.partner.AbstractAccountMethod;
import com.yonon.demo.design.partner.XFXJAccountService;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-12.
 */
public class XFXJAccountServiceImpl extends AbstractAccountMethod implements XFXJAccountService {

    /**
     * if else
     *
     * @param transCodeEnum
     * @param loan
     * @param objects
     */
    @Override
    public MQDomain partnerMatch(TransCodeEnum transCodeEnum, Loan loan, Object... objects) {
        return null;
    }
}
