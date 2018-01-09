package com.yonon.demo.design.account.common.service.impl;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.common.entity.PartnerAccountRule;
import com.yonon.demo.design.account.common.service.TransAccountAbstractService;
import com.yonon.demo.design.account.common.service.TransAccountBaseService;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.account.enums.TransFeeEnum;
import com.yonon.demo.design.account.enums.TransRuleEnum;
import com.yonon.demo.design.account.utils.FeeScaleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public class ACServiceImpl extends TransAccountAbstractService implements TransAccountBaseService {
    private MQDomain mqDomain;

    private static Logger logger = LoggerFactory.getLogger(ACServiceImpl.class);

    @Override
    public MQDomain genMQDomain(TransCodeEnum transCodeEnum, Loan loan, PartnerAccountRule partnerAccountRule, TransFeeEnum transFeeEnum) {
        logger.info("fas记账_AC_开始_loanNo:{},partnerCode:{},transCodeEnum:{}", loan.getId(), loan.getThird(), transCodeEnum.getCode());
        try {
            String acType = partnerAccountRule.getTransCodeAC();
            TransRuleEnum enumByCode = TransRuleEnum.getEnumByCode(acType);
            System.out.println("AC.." + loan.getName());
            /**
             * 通过父类 反射计费的方法
             * */
            Method method = methodMap.get(transFeeEnum.getCode());
            method.invoke(new ACServiceImpl(), enumByCode);
            logger.info("fas记账_AC_正常结束_loanNo:{},partnerCode:{},transCodeEnum:{}", loan.getId(), loan.getThird(), transCodeEnum.getCode());

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    protected MQDomain intMethod(TransRuleEnum enumByCode) {
        System.out.println("intMethod" + FeeScaleUtil.transAccountAllot(enumByCode, null).toString());
        return null;
    }

    @Override
    protected MQDomain ointMethod(TransRuleEnum enumByCode) {
        System.out.println("ointMethod" + FeeScaleUtil.transAccountAllot(enumByCode, null).toString());
        return null;
    }

    @Override
    protected MQDomain intNotOintMethod(TransRuleEnum enumByCode) {
        System.out.println("intNotOintMethod" + FeeScaleUtil.transAccountAllot(enumByCode, null).toString());
        return null;
    }

    @Override
    protected MQDomain intAddOintMethod(TransRuleEnum enumByCode) {
        System.out.println("intAddOintMethod" + FeeScaleUtil.transAccountAllot(enumByCode, null).toString());
        return null;
    }

    @Override
    protected MQDomain intAndOintMethod(TransRuleEnum enumByCode) {
        System.out.println("intAndOintMethod" + FeeScaleUtil.transAccountAllot(enumByCode, null).toString());
        return null;
    }
}
