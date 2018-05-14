package com.yonon.demo.design.partner;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.common.entity.MQDomain;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-12.
 */
public abstract class AbstractAccountMethod implements PartnerBaseService {
    protected static Map<String, Method> methodMap;

    static {
        methodMap = new HashMap<String, Method>();
        try {
            Method[] methods = AbstractAccountMethod.class.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
                methodMap.put(method.getName(), method);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    protected void cashACMethod(Loan loan, MQDomain mqDomain) {
        System.out.println("abstract cashACMethod" + "loanId:" + loan.getId());
    }

    protected void termACMethod(Loan loan, MQDomain mqDomain) {
        System.out.println("abstract termACMethod" + "loanId:" + loan.getId());
    }
}
