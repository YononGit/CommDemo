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
     * 4-�޸�constant partnerCode
     */
    public static void main(String[] args) {
       // readAccountCode();
        readTransaction();
    }
    private static void readAccountCode(){
        try {
            String file = "D:\\tmp\\018��Ŀ.xls";
            AccountCodeUtils utils = new AccountCodeUtils();
            utils.readAccountCodeExcel(file);
            System.out.printf("main method run complete!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static void readTransaction(){
        try {
            String file = "D:\\tmp\\018��¼.xlsx";
            TransactionUtils utils = new TransactionUtils();
            utils.readBusConfigExcel(file);
            utils.readTransactionConfigExcel(file);
            utils.readTransactionAccountExcel(file);
            System.out.printf("main method run complete!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


