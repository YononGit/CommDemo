package com.yonon.demo.design.partner.test;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-12.
 */

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.partner.PartnerAccountService;
import com.yonon.demo.design.partner.PartnerBaseService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public class PartnerTest {


    @Test
    public void testAccount() {
        System.out.println();
        Loan loan = new Loan("20", "THIRD_JS", "晋商", "00");
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:ApplicationContext.xml");
        PartnerAccountService partnerAccountService = (PartnerAccountService) ctx.getBean("partnerAccountService");
        partnerAccountService.transAccount(TransCodeEnum.AC, loan);
    }
}

// abstract termACMethodloanId:21
// JSAccountServiceImplloanId:20