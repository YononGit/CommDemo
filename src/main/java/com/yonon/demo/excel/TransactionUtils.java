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
 * 1-��������Ҫ��excel��[]����
 * 2-���������Ҫ��THIRD\QY\ALL����
 * 3-�ļ����Ŀ¼D:\\tmp\\
 * 4-�޸�constant partnerCode
 */
class TransactionUtils {
    private static Workbook wb = null;

    /**
     * ������Ŀ ��Ҫ���[] ��־
     **/
    public void readBusConfigExcel(String file) throws Exception {
        Workbook wb;
        String sql = "";
        String headSql = "-- ҵ������\n" +
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
                Sheet sheet = wb.getSheetAt(sheetNo); // ��һ��������

                int firstRowIndex = sheet.getFirstRowNum() + 1; // �õ���һ�е��±꣬�˴���2Ŀ����Ϊ�˱����ȡ�����
                int lastRowIndex = sheet.getLastRowNum();       // �õ����һ�е��±� ������֮�����������
                String snapShot = "snapShot";
                // ѭ����ȡ����
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
                        continue;//����пգ�ֱ���������У������ȡ��һ������
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
        String headSql = "-- ��������\n" +
                "DELETE FROM transaction_config WHERE transation_no LIKE '360JIETIAO_CASH" + Constant.partnerCode + "%';\n" +
                "DELETE FROM transaction_config WHERE transation_no LIKE '360JIETIAO_TERM" + Constant.partnerCode + "%';\n";
        String resultSql = headSql.concat("INSERT INTO transaction_config(transation_no,business_no,fin_code,fin_name,TYPE,fee_code,own_rate,partner_rate,xd_rate,use_rate,date_effective,date_invalid) VALUES\n");
        try {
            wb = ExcelUtilsHelps.getWorkbook(file);
            for (int sheetNo = 0; sheetNo < sheetNum; sheetNo++) {
                Sheet sheet = wb.getSheetAt(sheetNo); // ��һ��������
                String transactionNoPre = "";
                if (sheetNo == 0) {
                    transactionNoPre = "360JIETIAO_CASH";
                } else if (sheetNo == 1) {
                    transactionNoPre = "360JIETIAO_TERM";
                }

                int firstRowIndex = sheet.getFirstRowNum() + 1; // �õ���һ�е��±꣬�˴���2Ŀ����Ϊ�˱����ȡ�����
                int lastRowIndex = sheet.getLastRowNum();       // �õ����һ�е��±� ������֮�����������

                // ѭ����ȡ����
                String transCode = "";
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    Row row = sheet.getRow(rIndex);
                    int firstCellIndex = row.getFirstCellNum();   // �õ���һ��
//                    int lastCellIndex = row.getLastCellNum();   // �õ����һ�У�����֮����������
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
        String headSql = "-- ���׷�¼\n" +
                "DELETE FROM transaction_account WHERE transation_no LIKE '360JIETIAO_CASH" + Constant.partnerCode + "%';\n" +
                "DELETE FROM transaction_account WHERE transation_no LIKE '360JIETIAO_TERM" + Constant.partnerCode + "%';\n";
        String resultSql = headSql.concat("INSERT INTO transaction_account(transation_no,seq_no,account_code,summary_code,direction,amt_cal_type,is_default_account) VALUES\n");
        try {
            wb = ExcelUtilsHelps.getWorkbook(file);
            for (int sheetNo = 0; sheetNo < sheetNum; sheetNo++) {
                Sheet sheet = wb.getSheetAt(sheetNo); // ��һ��������
                String transactionNoPre = "";
                if (sheetNo == 0) {
                    transactionNoPre = "360JIETIAO_CASH";
                } else if (sheetNo == 1) {
                    transactionNoPre = "360JIETIAO_TERM";
                }

                int firstRowIndex = sheet.getFirstRowNum() + 1; // �õ���һ�е��±꣬�˴���2Ŀ����Ϊ�˱����ȡ�����
                int lastRowIndex = sheet.getLastRowNum();       // �õ����һ�е��±� ������֮�����������

                Integer reqNo = 1;
                // ѭ����ȡ����
                String transCode = "";
                String finName = "";
                String direction = "";
                String accountCode = "";
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    Row row = sheet.getRow(rIndex);
                    int firstCellIndex = row.getFirstCellNum();   // �õ���һ��
//                    int lastCellIndex = row.getLastCellNum();   // �õ����һ�У�����֮����������
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
     * ���������� ��Ҫ���THIRD  �� QY��־
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
