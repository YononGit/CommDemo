package com.yonon.demo.design.account.common.service;

import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.enums.TransFeeEnum;
import com.yonon.demo.design.account.enums.TransRuleEnum;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-05.
 */
public abstract class TransAccountAbstractService {
    protected static Map<String, Method> methodMap;

    static {
        methodMap = new HashMap<String, Method>();
        try {
            for (TransFeeEnum transFeeEnum : TransFeeEnum.values()) {
                String methodName = transFeeEnum.getCode().concat("Method");
                System.out.println(methodName);
                methodMap.put(transFeeEnum.getCode(), TransAccountAbstractService.class.getDeclaredMethod(methodName, TransRuleEnum.class));
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    protected MQDomain intMethod(TransRuleEnum enumByCode) {
        System.out.println("abstract intMethod");
        return null;
    }

    protected MQDomain ointMethod(TransRuleEnum enumByCode) {
        System.out.println("abstract intMethod");
        return null;
    }

    protected MQDomain intNotOintMethod(TransRuleEnum enumByCode) {
        System.out.println("abstract intMethod");
        return null;
    }

    protected MQDomain intAddOintMethod(TransRuleEnum enumByCode) {
        System.out.println("abstract intMethod");
        return null;
    }

    protected MQDomain intAndOintMethod(TransRuleEnum enumByCode) {
        System.out.println("abstract intMethod");
        return null;
    }

}
