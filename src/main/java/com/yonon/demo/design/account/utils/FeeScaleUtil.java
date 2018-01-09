package com.yonon.demo.design.account.utils;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.enums.TransRuleEnum;

import java.math.BigDecimal;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-05.
 */
public class FeeScaleUtil {

    public static BigDecimal transAccountAllot(TransRuleEnum enumByCode, Loan loan /**, lcs Fee NUM*/) {
        BigDecimal result = BigDecimal.ONE;
        switch (enumByCode) {
            case NORMAL:
                System.out.println("NORMAL");
                result = BigDecimal.ONE;
                break;
            case ALLOT:
                System.out.println("ALLOT");
                result = new BigDecimal(5);
                break;
            case THIRD:
                System.out.println("THIRD");
                result = BigDecimal.TEN;
                break;
            default:
                break;
        }
        return result;
    }
}
