package com.yonon.demo.design.account.common.service;

import com.yonon.demo.design.account.common.entity.Loan;
import com.yonon.demo.design.account.enums.TransCodeEnum;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-02.
 */
public interface TransAccountService {
    void transAccount(TransCodeEnum transCodeEnum, Loan loan, Object... objects);
}
