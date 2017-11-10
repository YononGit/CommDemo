package com.yonon.demo.excel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jr-jiangyinghan on 2017-9-30.
 */
public class ExcelMain {
    private static Logger logger = LoggerFactory.getLogger(ExcelMain.class);
    /***
     *
     * 1-交易码需要在excel用[]括起
     * 2-分配比例需要加THIRD\QY\ALL修饰
     * 3-文件存放目录D:\\tmp\\
     * 4-修改constant partnerCode
     */
    public static void main(String[] args) throws Exception{
        readTransaction();
        readAccountCode();
        Runtime.getRuntime().exec("cmd /c start D:\\sql");
    }
    private static void readAccountCode(){
        try {
            String file = "D:\\tmp\\018科目.xls";
            AccountCodeUtils utils = new AccountCodeUtils();
            utils.readAccountCodeExcel(file);
            logger.info("main method run complete!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static void readTransaction(){
        try {
            String file = "D:\\tmp\\018分录.xlsx";
            TransactionUtils utils = new TransactionUtils();
            utils.readBusConfigExcel(file);
            utils.readTransactionConfigExcel(file);
            utils.readTransactionAccountExcel(file);
            logger.info("main method run complete!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


