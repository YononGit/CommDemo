package com.yonon.demo.excel;


/**
 * Created by jr-jiangyinghan on 2017-9-30.
 */
public class ExcelMain {
    /***
     *
     * 1-��������Ҫ��excel��[]����
     * 2-���������Ҫ��THIRD\QY\ALL����
     * 3-�ļ����Ŀ¼D:\\tmp\\
     */
    public static void main(String[] args) {
        try {
            String file = "D:\\tmp\\016��¼.xlsx";
            String partnerCode = "016";
            TransactionUtils utils = new TransactionUtils();
            utils.setPartnerCode(partnerCode);
            utils.readBusConfigExcel(file);
            utils.readTransactionConfigExcel(file);
            utils.readTransactionAccountExcel(file);
            System.out.printf("main method run complete!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}


