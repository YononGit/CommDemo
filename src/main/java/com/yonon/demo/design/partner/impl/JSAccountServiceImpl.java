package com.yonon.demo.design.partner.impl;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.account.enums.TransPatternEnum;
import com.yonon.demo.design.partner.AbstractAccountMethod;
import com.yonon.demo.design.partner.JSAccountService;
import com.yonon.demo.design.partner.common.PatternTools;

import java.lang.reflect.Method;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-12.
 */
public class JSAccountServiceImpl extends AbstractAccountMethod implements JSAccountService {

    @Override
    protected void cashACMethod(Loan loan, MQDomain mqDomain) {
        System.out.println("JSAccountServiceImpl" + "loanId:" + loan.getId());
    }


    /**
     * if else
     *
     * @param transCodeEnum
     * @param loan
     * @param objects
     */
    @Override
    public MQDomain partnerMatch(TransCodeEnum transCodeEnum, Loan loan, Object... objects) {
        try {
            TransPatternEnum transPatternEnum = PatternTools.getTransPattern(loan);
            String methodName = transPatternEnum.getPattern().concat(transCodeEnum.getCode()).concat("Method");
            Method method = methodMap.get(methodName);
            method.invoke(new JSAccountServiceImpl(), loan, new MQDomain(), objects);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void aheadCompensate() {

    }
}
