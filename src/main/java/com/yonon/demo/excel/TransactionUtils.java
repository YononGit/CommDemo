package com.yonon.demo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import static com.yonon.demo.excel.Constant.sheetNum;

/**
 * Created by jr-jiangyinghan on 2017-10-10.
 *
 * 1-交易码需要在excel用[]括起
 * 2-分配比例需要加THIRD\QY\ALL修饰
 * 3-文件存放目录D:\\tmp\\
 * 4-修改constant partnerCode
 */
class TransactionUtils {
    private static Workbook wb = null;

    /**
     * 交易栏目 需要添加[] 标志
     **/
    public void readBusConfigExcel(String file) throws Exception {
        Workbook wb;
        String sql = "";
        String headSql = "-- 业务配置\n" +
                "DELETE FROM business_config WHERE business_no LIKE 'b360JIETIAO_CASH" + Constant.partnerCode + "%';\n" +
                "DELETE FROM business_config WHERE business_no LIKE 'b360JIETIAO_TERM" + Constant.partnerCode + "%';\n";
        String resultSql = headSql.concat("INSERT INTO business_config(business_no,trans_code,NAME,partner_code,product_code) VALUES\n");
        try {
            wb = ExcelUtilsHelps.getWorkbook(file);
            for (int sheetNo = 0; sheetNo < sheetNum; sheetNo++) {
                String produceCode = "";
                if (sheetNo == 0) {
                    produceCode = "360JIETIAO_CASH";
                } else if (sheetNo == 1) {
                    produceCode = "360JIETIAO_TERM";
                }
                Sheet sheet = wb.getSheetAt(sheetNo); // 第一个工作表

                int firstRowIndex = sheet.getFirstRowNum() + 1; // 得到第一行的下标，此处加2目的是为了避免读取活动首行
                int lastRowIndex = sheet.getLastRowNum();       // 得到最后一行的下标 ，他们之差代表总行数
                String snapShot = "snapShot";
                // 循环读取数据
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    Row row = sheet.getRow(rIndex);
                    Cell cell = row.getCell(firstRowIndex - 1);
                    if (cell != null) {
                        String cellStr = cell.toString();
                        if (StringUtils.isEmpty(cellStr) || snapShot.equals(cellStr)) {
                            continue;
                        } else {
                            String transCode = getTransCodePrefix(cellStr);
                            String name = getNamePrefix(cellStr);
                            sql += genBusConfigSql(transCode, name, produceCode, Constant.partnerCode).concat("\n");
                            snapShot = cellStr;
                        }
                    } else {
                        continue;//如果有空，直接跳过本行，进入读取下一行数据
                    }
                }
                if (sheetNo == sheetNum - 1) {
                    resultSql = ExcelUtilsHelps.formatAndWriteSql(sql, resultSql, "01-busConfig");
                    System.out.println(resultSql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String getTransCodePrefix(String content) {
        int beginIndex = content.indexOf("[");
        int endIndex = content.indexOf("]");
        String result = content.substring(beginIndex + 1, endIndex);
        return result;
    }

    private String getNamePrefix(String content) {
        int beginIndex = 0;
        int endIndex = content.indexOf("[");
        String result = content.substring(beginIndex, endIndex);
        return result;
    }

    private String genBusConfigSql(String transCode, String name, String produceCode, String partnerCode) {
        String businessNo = "b".concat(produceCode).concat(partnerCode).concat(transCode);
        String sql = "('" + businessNo + "','" + transCode + "','" + name + "','" + partnerCode + "','" + produceCode + "'),";
        sql = sql.replaceAll("\n", "");
        return sql;
    }

    public void readTransactionConfigExcel(String file) throws Exception {
        Workbook wb;
        String sql = "";
        String headSql = "-- 交易配置\n" +
                "DELETE FROM transaction_config WHERE transation_no LIKE '360JIETIAO_CASH" + Constant.partnerCode + "%';\n" +
                "DELETE FROM transaction_config WHERE transation_no LIKE '360JIETIAO_TERM" + Constant.partnerCode + "%';\n";
        String resultSql = headSql.concat("INSERT INTO transaction_config(transation_no,business_no,fin_code,fin_name,TYPE,fee_code,own_rate,partner_rate,xd_rate,use_rate,date_effective,date_invalid) VALUES\n");
        try {
            wb = ExcelUtilsHelps.getWorkbook(file);
            for (int sheetNo = 0; sheetNo < sheetNum; sheetNo++) {
                Sheet sheet = wb.getSheetAt(sheetNo); // 第一个工作表
                String transactionNoPre = "";
                if (sheetNo == 0) {
                    transactionNoPre = "360JIETIAO_CASH";
                } else if (sheetNo == 1) {
                    transactionNoPre = "360JIETIAO_TERM";
                }

                int firstRowIndex = sheet.getFirstRowNum() + 1; // 得到第一行的下标，此处加2目的是为了避免读取活动首行
                int lastRowIndex = sheet.getLastRowNum();       // 得到最后一行的下标 ，他们之差代表总行数

                // 循环读取数据
                String transCode = "";
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    Row row = sheet.getRow(rIndex);
                    int firstCellIndex = row.getFirstCellNum();   // 得到第一列
//                    int lastCellIndex = row.getLastCellNum();   // 得到最后一列，他们之差是总列数
                    for (int cIndex = firstCellIndex; cIndex < 2; cIndex++) {
//                        String finCode = "";
                        Cell cell = row.getCell(cIndex);
                        String content = cell.toString();
                        if (content.contains("[")) {
                            transCode = getTransCodePrefix(content);
                            continue;
                        }
                        if (!StringUtils.isEmpty(content)) {
                            sql += genTransactionConfigSql(content, transCode, Constant.partnerCode, transactionNoPre);
                            sql = sql.concat("\n");
                        }
                    }
                }
                if (sheetNo == sheetNum - 1) {
                    resultSql = ExcelUtilsHelps.formatAndWriteSql(sql, resultSql, "02-transactionConfig");
                    System.out.println(resultSql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String genTransactionConfigSql(String content, String transCode, String partnerCode, String transactionNoPre) {
        String finCode = FinCode.getFinCodeByName(content);
        String transactionNo = transactionNoPre.concat(partnerCode).concat(transCode).concat(finCode);
        String businessNo = "b".concat(transactionNoPre).concat(partnerCode).concat(transCode);
        String finName = content;
        String type = "01";
        String feeCode = finCode;
        String ownRate = "1";
        String partnerRate = "0";
        String xdRate = "0";
        String useRate = "N";
        String dateEffective = "2016-11-01";
        String dateInvalid = "2999-11-01";
        String sql = "('" + transactionNo + "','" + businessNo + "','" + finCode
                + "','" + finName + "','" + type + "','" + feeCode + "','" +
                ownRate + "','" + partnerRate + "','" + xdRate + "','" + useRate + "','" + dateEffective + "','" + dateInvalid + "'),";
        return sql;
    }

    public void readTransactionAccountExcel(String file) {
        Workbook wb;
        String sql = "";
        String headSql = "-- 交易分录\n" +
                "DELETE FROM transaction_account WHERE transation_no LIKE '360JIETIAO_CASH" + Constant.partnerCode + "%';\n" +
                "DELETE FROM transaction_account WHERE transation_no LIKE '360JIETIAO_TERM" + Constant.partnerCode + "%';\n";
        String resultSql = headSql.concat("INSERT INTO transaction_account(transation_no,seq_no,account_code,summary_code,direction,amt_cal_type,is_default_account) VALUES\n");
        try {
            wb = ExcelUtilsHelps.getWorkbook(file);
            for (int sheetNo = 0; sheetNo < sheetNum; sheetNo++) {
                Sheet sheet = wb.getSheetAt(sheetNo); // 第一个工作表
                String transactionNoPre = "";
                if (sheetNo == 0) {
                    transactionNoPre = "360JIETIAO_CASH";
                } else if (sheetNo == 1) {
                    transactionNoPre = "360JIETIAO_TERM";
                }

                int firstRowIndex = sheet.getFirstRowNum() + 1; // 得到第一行的下标，此处加2目的是为了避免读取活动首行
                int lastRowIndex = sheet.getLastRowNum();       // 得到最后一行的下标 ，他们之差代表总行数

                Integer reqNo = 1;
                // 循环读取数据
                String transCode = "";
                String finName = "";
                String direction = "";
                String accountCode = "";
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    Row row = sheet.getRow(rIndex);
                    int firstCellIndex = row.getFirstCellNum();   // 得到第一列
//                    int lastCellIndex = row.getLastCellNum();   // 得到最后一列，他们之差是总列数
                    for (int cIndex = firstCellIndex; cIndex < 9; cIndex++) {
                        Cell cell = row.getCell(cIndex);
                        String content = cell.toString();
                        // System.out.println(content);
                        if (content.contains("[")) {
                            reqNo = 1;
                            transCode = getTransCodePrefix(content);
                        }
                        if (cIndex == 1 && !StringUtils.isEmpty(content)) {
                            finName = content;
                        }
                        if (cIndex == 3 && !StringUtils.isEmpty(content)) {
                            direction = content;
                        }
                        if (cIndex == 4 && !StringUtils.isEmpty(content)) {
                            accountCode = content;
                        }
                        if (cIndex == 8) {
                            sql += genTransactionAccountSql(finName, content, transCode, transactionNoPre, Constant.partnerCode, direction, accountCode, reqNo).concat("\n");
                        }
                    }
                    reqNo++;
                }
                if (sheetNo == sheetNum - 1) {
                    resultSql = ExcelUtilsHelps.formatAndWriteSql(sql, resultSql, "03-transactionAccount");
                    System.out.println(resultSql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 比例分配栏 需要添加THIRD  和 QY标志
     */
    private String genTransactionAccountSql(String finName, String content, String transCode, String transactionNoPre, String partnerCode, String direction, String accountCode, Integer reqNo) {
        String finCode = FinCode.getFinCodeByName(finName);
        if (StringUtils.isEmpty(finCode)) {
            System.out.println(finName + " can not found relevant finCode!");
        }
        String transactionNo = transactionNoPre.concat(partnerCode).concat(transCode).concat(finCode);
        String amtCalType = "03";
        String isDefaultAccount = "Y";
        String summaryCode = "";
        if (content.contains(Constant.THIRD)) {
            amtCalType = "002";
        } else if (content.contains(Constant.QY)) {
            amtCalType = "001";
        }
        if (content.contains(Constant.NO_COMPENSATORY)) {
            isDefaultAccount = "N";
        }

        if (transactionNo.contains("DB")) {
            summaryCode = "TEMPLATE_001";
        } else if (transactionNo.contains("TRAN_DEDUCT")) {
            summaryCode = "TEMPLATE_003";
        } else {
            summaryCode = "TEMPLATE_002";
        }
        direction = Direction.getCodeByName(direction);
        String sql = "('" + transactionNo + "','" + String.valueOf(reqNo) + "','" + accountCode + "','"
                + summaryCode + "','" + direction
                + "','" + amtCalType + "','" + isDefaultAccount + "'),";
        return sql;
    }


}
