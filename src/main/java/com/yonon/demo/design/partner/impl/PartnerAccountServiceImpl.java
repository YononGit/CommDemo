package com.yonon.demo.design.partner.impl;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.enums.PartnerCodeEnum;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.partner.PartnerAccountService;
import com.yonon.demo.design.partner.PartnerBaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-12.
 */
@Service("partnerAccountService")
public class PartnerAccountServiceImpl implements PartnerAccountService {

    @Override
    public void transAccount(TransCodeEnum transCodeEnum, Loan loan, Object... objects) {
        PartnerCodeEnum partnerCodeEnum = PartnerCodeEnum.getEnumByThirdCode(loan.getThird());
        PartnerBaseService partnerBaseService = getPartnerTransService(partnerCodeEnum.getBeanPrefix());
        MQDomain mqDomain = partnerBaseService.partnerMatch(transCodeEnum, loan, objects);
        // save
        // sendMq
    }

    @Override
    public void transAccountExt() {

    }

    //
    // 从入口
    private void aVoid() {
    }

    private PartnerBaseService getPartnerTransService(String beanPrefix) {
        String beanName = "AccountService";
        PartnerBaseService partnerBaseService;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:ApplicationContext.xml");
        partnerBaseService = (PartnerBaseService) ctx.getBean(beanPrefix.concat(beanName));
        return partnerBaseService;
    }


}
