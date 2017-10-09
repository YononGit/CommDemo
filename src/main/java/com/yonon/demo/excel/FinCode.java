package com.yonon.demo.excel;

/**
 * Created by jr-jiangyinghan on 2017-10-9.
 */
enum FinCode {
    PRINT("PRINT", "本金"),
    PRINT_EXT("PRINT", "放款"),
    FEE2("FEE2", "借款交易费"),
    FEE3("FEE3", "还款交易费"),
    FEE4("FEE4", "首贷佣金"),
    FEE5("FEE5", "再贷佣金"),
    FEE6("FEE6", "违约金"),
    INT("INT", "利息"),
    INT_EXT("INT", "分期手续费"),
    OINT("OINT", "罚息"),
    TRAN_DEDUCT("TRAN_DEDUCT", "利息券");

    public String getFinCode() {
        return finCode;
    }

    public String getFinName() {
        return finName;
    }

    private String finCode;
    private String finName;

    FinCode(String finCode, String finName) {
        this.finCode = finCode;
        this.finName = finName;
    }

    public static String getFinCodeByName(String finName) {
        for (FinCode code : FinCode.values()) {
            if (code.getFinName().equals(finName))
                return code.getFinCode();
        }
        return null;
    }
}
