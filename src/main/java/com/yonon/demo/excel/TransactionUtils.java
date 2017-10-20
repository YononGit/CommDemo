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
 * Created by jr-jiangyinghan on 2017-10-10.
 * <p>
 * 1-��������Ҫ��excel��[]����
 * 2-���������Ҫ��THIRD\QY\ALL����
 * 3-�ļ����Ŀ¼D:\\tmp\\
 */
class TransactionUtils {
    private static Workbook wb = null;

    public static void setPartnerCode(String partnerCode) {
        TransactionUtils.partnerCode = partnerCode;
    }

   private  static String partnerCode = "013";

    private Workbook getWorkbook(String file) {
        try {
            if (wb == null) {
                System.out.println("��ȡwb����");
                InputStream inputStream = new FileInputStream(file);
                if (file.endsWith("xls")) {
                    wb = new HSSFWorkbook(inputStream); // ����xls��ʽ
                } else if (file.endsWith("xlsx")) {
                    wb = new XSSFWorkbook(inputStream); // ����xlsx��ʽ
                }
                return wb;
            } else {
                System.out.println("����wb");
                return wb;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return wb;
        }
    }


    /**
     * ������Ŀ ��Ҫ���[] ��־
     **/
    public void readBusConfigExcel(String file) throws Exception {
        Workbook wb;
        String sql = "";
        String resultSql = "INSERT INTO business_config(business_no,trans_code,NAME,partner_code,product_code) VALUES\n";
        try {
            wb = getWorkbook(file);
            for (int sheetNo = 0; sheetNo <= 1; sheetNo++) {
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
                            sql += genBusConfigSql(transCode, name, produceCode, partnerCode).concat("\n");
                            snapShot = cellStr;
                        }
                    } else {
                        continue;//����пգ�ֱ���������У������ȡ��һ������
                    }
                }
                if (sheetNo != 0) {
                    resultSql = formatAndWriteSql(sql, resultSql, "01-busConfig");
                    System.out.println(resultSql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatAndWriteSql(String sql, String resultSql, String fileType) {
        int sqlCharCount = sql.length();
        sql = sql.substring(0, sqlCharCount - 2);
        sql = sql.concat(";");
        resultSql = resultSql.concat(sql);
        try {
            writeFile(resultSql, fileType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSql;
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
        String resultSql = "INSERT INTO transaction_config(transation_no,business_no,fin_code,fin_name,TYPE,fee_code,own_rate,partner_rate,xd_rate,use_rate,date_effective,date_invalid) VALUES\n";
        try {
            wb = getWorkbook(file);
            for (int sheetNo = 0; sheetNo < 2; sheetNo++) {
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
                            sql += genTransactionConfigSql(content, transCode, partnerCode, transactionNoPre);
                            sql = sql.concat("\n");
                        }
                    }
                }
                if (sheetNo != 0) {
                    resultSql = formatAndWriteSql(sql, resultSql, "02-transactionConfig");
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
        String resultSql = "INSERT INTO transaction_account(transation_no,seq_no,account_code,summary_code,direction,amt_cal_type,is_default_account) VALUES\n";
        try {
            wb = getWorkbook(file);
            for (int sheetNo = 0; sheetNo < 2; sheetNo++) {
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
                            sql += genTransactionAccountSql(finName, content, transCode, transactionNoPre, partnerCode, direction, accountCode, reqNo).concat("\n");
                        }
                    }
                    reqNo++;
                }
                if (sheetNo != 0) {
                    resultSql = formatAndWriteSql(sql, resultSql, "03-transactionAccount");
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
        if(StringUtils.isEmpty(finCode)){
            System.out.println(finName + " can not found relevant finCode!");
        }
        String transactionNo = transactionNoPre.concat(partnerCode).concat(transCode).concat(finCode);
        String amtCalType = "03";
        String isDefaultAccount = "Y";
        String summaryCode = "";
        if (content.contains("THIRD")) {
            amtCalType = "002";
        } else if (content.contains("QY")) {
            amtCalType = "001";
        }
        if (content.contains("�Ǵ���")) {
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
//        System.out.println("transactionNo: " + transactionNo);
//        System.out.println("direction: " + direction);
//        System.out.println("accountCode: " + accountCode);
//        System.out.println("amtCalType: " + amtCalType);
        String sql = "('" + transactionNo + "','" + String.valueOf(reqNo) + "','" + accountCode + "','"
                + summaryCode + "','" + direction
                + "','" + amtCalType + "','" + isDefaultAccount + "'),";
//        System.out.println(sql);
        return sql;
    }

    /**
     * д���ļ�
     */
    private void writeFile(String resultSql, String fileType) throws IOException {
        File file;
        FileWriter fileWriter = null;
        BufferedWriter bufferWriter = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String fileName = "D:/sql/".concat(fileType).concat(sdf.format(new Date())).concat(".sql");
            file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile();
            fileWriter = new FileWriter(fileName, false);
            bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(resultSql);
            bufferWriter.flush();
            bufferWriter.close();
            fileWriter.close();

            System.out.println("write sql to file complete: " + fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bufferWriter.close();
            fileWriter.close();
        }
    }
}
