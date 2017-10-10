package com.yonon.demo.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jr-jiangyinghan on 2017-9-30.
 */
public class ExcelMain {
    /***
     *
     * 1-交易码需要在excel用[]括起
     * 2-分配比例需要加THIRD或者QY修饰
     * 3-文件存放目录D:\\tmp\\
     */
    public static void main(String[] args) {
        try {
            String file = "D:\\tmp\\013分录.xlsx";
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


