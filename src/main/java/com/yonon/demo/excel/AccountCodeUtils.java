package com.yonon.demo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-11-06.
 */
public class AccountCodeUtils {
    private static Workbook wb = null;
    private static Logger logger = LoggerFactory.getLogger(AccountCodeUtils.class);
    /**
     * 科目
     **/
    public void readAccountCodeExcel(String file) throws Exception {
        Workbook wb;
        try {
            wb = ExcelUtilsHelps.getWorkbook(file);
            String produceCode = "";
            String sql = "";
            String headSql = "-- 业务配置\n" +
                    "DELETE FROM account_title WHERE CODE IN(\n";
            String insertSql = "-- 新增科目\n" +
                    "INSERT INTO account_title(CODE,NAME,parent_code,LEVEL,TYPE,code_rule,code_owner,method,direction,charge_acc_type,is_allow_manual,is_allow_transfer,STATUS) VALUES\n";
            String resultSql;

            // 第一个工作表
            Sheet sheet = wb.getSheetAt(0);
            // 得到第一行的下标，此处加2目的是为了避免读取活动首行
            int firstRowIndex = sheet.getFirstRowNum() + 1;
            // 得到最后一行的下标 ，他们之差代表总行数
            int lastRowIndex = sheet.getLastRowNum();
            String snapShot = "snapShot";
            String baseCode = "";
            String parentCode = "";
            String accountCode = "";
            String accountName = "";

            // 循环读取数据
            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                Row row = sheet.getRow(rIndex);
                int firstCellIndex = row.getFirstCellNum();   // 得到第一列

                String direction = "";
                String type = "";
                String codeRule = "";
                String chargeAccType = "";
                String codeOwner = "";
                String method = "";
                String isAllowManual = "";
                String isAllowTransfer = "";
                for (int cIndex = firstCellIndex; cIndex < 10; cIndex++) {
                    Cell cell = row.getCell(cIndex);
                    if (cell != null) {
                        String cellStr = cell.toString();
                        if (cIndex == 0 && !StringUtils.isEmpty(cellStr)) {
                            baseCode = cellStr;
                            //获取科目枚举
                            String accountEnumCode = AccountCode.getCodeByName(baseCode);
                            if (StringUtils.isEmpty(accountEnumCode)) {
                                logger.error("can not get accountCode enum,baseCode: {}" , baseCode);
                            } else {
                                String[] codes = accountEnumCode.split("-");
                                direction = codes[0];
                                type = codes[1];
                                codeRule = codes[2];
                                chargeAccType = codes[3];
                                codeOwner = codes[4];
                                method = codes[5];
                                isAllowManual = codes[6];
                                isAllowTransfer = codes[7];
                            }
                        }
                        if (cIndex == 2 && !StringUtils.isEmpty(cellStr)) {
                            parentCode = cellStr;
                        }
                        if (cIndex == 4 && !StringUtils.isEmpty(cellStr)) {
                            accountCode = cellStr;
                            headSql = headSql.concat("'" + accountCode + "',").concat("\n");
                        }
                        if (cIndex == 5 && !StringUtils.isEmpty(cellStr)) {
                            accountName = cellStr;
                        }
                    } else {
                        continue;//如果有空，直接跳过本行，进入读取下一行数据
                    }
                }
                sql += genAccountTitleSql(accountCode, accountName, parentCode, type, codeRule, codeOwner, method, direction, chargeAccType, isAllowManual, isAllowTransfer).concat("\n");

            }
            resultSql = headSql.substring(0, headSql.length() - 2).concat(");\n");
            sql = insertSql.concat(sql);
            //sql = headSql.concat(resultSql);
            resultSql = ExcelUtilsHelps.formatAndWriteSql(sql, resultSql, "04-accountTitle");
            System.out.println(resultSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String genAccountTitleSql(String accountCode, String accountName, String parentCode, String type, String codeRule, String codeOwner, String method, String direction, String chargeAccType, String isAllowManual, String isAllowTransfer) {
        String sql = "('" + accountCode + "','" + accountName + "','" + parentCode + "','" + "3" + "','" + type + "','" + codeRule + "','" + codeOwner + "','" + method + "','" + direction + "','" + chargeAccType + "','" + isAllowManual + "','" + isAllowTransfer + "','"  + "Y" + "'),";
        sql = sql.replaceAll("\n", "");
        return sql;
    }

}
