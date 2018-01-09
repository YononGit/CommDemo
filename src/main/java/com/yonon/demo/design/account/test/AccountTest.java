package com.yonon.demo.design.account.test;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.service.TransAccountService;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.annotation.Resource;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
public class AccountTest {

    private TransAccountService transAccountService;

    @Test
    public void testAccount() {
        System.out.println();
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:ApplicationContext.xml");
        transAccountService = (TransAccountService) ctx.getBean("transAccountService");
        transAccountService.transAccount(TransCodeEnum.CP, new Loan("2018", "Year", "XFXJ"));
        System.out.println();
    }
}
