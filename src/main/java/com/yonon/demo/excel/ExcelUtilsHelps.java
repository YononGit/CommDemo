package com.yonon.demo.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-11-06.
 */
public class ExcelUtilsHelps {
    private static Workbook wb = null;
    private static String fileSnapshot = "";

    private static Logger logger = LoggerFactory.getLogger(ExcelUtilsHelps.class);
    /**
     * 获取wb对象
     *
     * @param file
     * @return
     */
    public static Workbook getWorkbook(String file) {
        try {
            if (wb == null || !file.equals(fileSnapshot)) {
                // snapshot
                logger.info("current file is:{},fileSnapshot is:{}",file,fileSnapshot);
                fileSnapshot = file;
                System.out.println("获取wb对象");
                InputStream inputStream = new FileInputStream(file);
                if (file.endsWith(Constant.FILETYPE_XLS)) {
                    // 解析xls格式
                    wb = new HSSFWorkbook(inputStream);
                } else if (file.endsWith(Constant.FILETYPE_XLSX)) {
                    // 解析xlsx格式
                    wb = new XSSFWorkbook(inputStream);
                }
                return wb;
            } else {
                System.out.println("返回wb");
                return wb;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return wb;
        }
    }

    /**
     * 格式化sql
     */
    public static String formatAndWriteSql(String sql, String resultSql, String fileType) {
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
    /**
     * 写入文件
     */
    public static void writeFile(String resultSql, String fileType) throws IOException {
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
