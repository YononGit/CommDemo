package com.yonon.demo.excel;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-11-06.
 *
 * direction - type - CODE_RULE - charge_acc_type - code_owner - method - is_allow_manual -	is_allow_transfer

 */
enum AccountCode {
    CODE_1122( "D-1-453-N-QY-AL-Y-N","1122"),
    CODE_2202( "C-2-453-N-QY-AL-Y-N","2202"),
    CODE_2241( "C-2-453-N-QY-AL-Y-N","2241"),
    CODE_4001( "C-5-453-N-QY-AL-Y-N","4001"),
   ;
    private String code;
    private String name;

    AccountCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getCodeByName(String name) {
        for (AccountCode accountCode : AccountCode.values()) {
            if (accountCode.getName().equals(name)) {
                return accountCode.getCode();
            }
        }
        return null;
    }
}

