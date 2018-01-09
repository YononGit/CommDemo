package com.yonon.demo.design.account.common.service.impl;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.common.entity.PartnerAccountRule;
import com.yonon.demo.design.account.common.service.TransAccountAbstractService;
import com.yonon.demo.design.account.common.service.TransAccountBaseService;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.account.enums.TransFeeEnum;
import com.yonon.demo.design.account.enums.TransRuleEnum;
import com.yonon.demo.design.account.third.service.TransCompensateService;
import com.yonon.demo.design.account.utils.FeeScaleUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public class CPServiceImpl extends TransAccountAbstractService implements TransAccountBaseService {
    @Resource
    private TransCompensateService transCompensateService;

    @Override
    public MQDomain genMQDomain(TransCodeEnum transCodeEnum, Loan object, PartnerAccountRule partnerAccountRule, TransFeeEnum transFeeEnum) {
        String cpType = partnerAccountRule.getTransCodeCP();
        TransRuleEnum enumByCode = TransRuleEnum.getEnumByCode(cpType);

        switch (transCodeEnum) {
            case PCP:
                preCompensate();
                break;
            case CP:
                if (TransRuleEnum.THIRD.equals(enumByCode)) {
                    System.out.println("third CP..");
                    special(object);
                } else {
                    System.out.println("normal/allot.. CP。。。");
                    /**
                     * 通过父类 反射计费的方法
                     * */
                    Method method = methodMap.get(transFeeEnum.getCode());
                    try {
                        method.invoke(new CPServiceImpl(), enumByCode);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println(ex.getMessage());
                    }
                }
                break;
            default:
                break;
        }
        return null;
    }

    // 提前代偿
    private MQDomain preCompensate() {
        System.out.println("pre..CP");
        return null;
    }

    private MQDomain special(Object object) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:ApplicationContext.xml");
        transCompensateService = (TransCompensateService) ctx.getBean("transCompensateService");

        return transCompensateService.transCompensate("013", object);
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
