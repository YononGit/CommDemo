package com.yonon.demo.excel;

/**
 * Created by jr-jiangyinghan on 2017-10-9.
 */
enum FinCode {
    PRINT("PRINT", "����"),
    PRINT_EXT("PRINT", "�ſ�"),
    FEE2("FEE2", "���׷�"),
    FEE3("FEE3", "����׷�"),
    FEE4("FEE4", "�״�Ӷ��"),
    FEE5("FEE5", "�ٴ�Ӷ��"),
    FEE6("FEE6", "ΥԼ��"),
    INT("INT", "��Ϣ"),
    INT_EXT("INT", "����������"),
    OINT("OINT", "��Ϣ"),
    TRAN_DEDUCT("TRAN_DEDUCT", "��Ϣȯ");

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
