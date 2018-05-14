package com.yonon.demo.design.partner.common;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.enums.TransPatternEnum;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-15.
 */
public class PatternTools {
    /**
     * 获取业务类型
     */
    public static TransPatternEnum getTransPattern(Loan loan) {
        TransPatternEnum transPatternEnum = TransPatternEnum.getEnumByCode(loan.getRpyType());
        return transPatternEnum;
    }
}
