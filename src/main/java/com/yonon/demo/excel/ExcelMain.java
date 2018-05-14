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
     * 1-��������Ҫ��excel��[]����
     * 2-���������Ҫ��THIRD\QY\ALL����
     * 3-�ļ����Ŀ¼D:\\tmp\\
     * 4-�޸�constant partnerCode
     * 5-�޸�constant sheetNo ����������
     * 6-�޸��ļ��Ǵ���ΪNC
     */
    public static void main(String[] args) throws Exception{
        readTransaction();
        readAccountCode();
        Runtime.getRuntime().exec("cmd /c start D:\\sql");
    }
    private static void readAccountCode(){
        try {
            String file = "D:\\tmp\\001��Ŀ.xls";
            AccountCodeUtils utils = new AccountCodeUtils();
            utils.readAccountCodeExcel(file);
            logger.info("main method run complete!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static void readTransaction(){
        try {
            String file = "D:\\tmp\\204��¼.xls";
            TransactionUtils utils = new TransactionUtils();
            utils.readBusConfigExcel(file);
            utils.readTransactionConfigExcel(file);
            utils.readTransactionAccountExcel(file);
            logger.info("main method run complete!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //1.0.0
    public void newAddFeature(){
        //
    }
    //1.2.0
    public void method120(){

    }
    //1.3.0
    public void method130(){

    }
    //1.4.0
    public void method140(){

    }
}


