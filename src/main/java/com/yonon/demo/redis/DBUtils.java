package com.yonon.demo.redis;

import com.yonon.demo.domain.DBRedisUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.*;
import java.util.Calendar;

/**
 * @author JiangYinghan 2017年1月4日
 */
public class DBUtils {
    private static Connection conn = null;

    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    public static Connection getConnect() {
        try {
            if (conn == null) {
                long startTime = System.currentTimeMillis();
                System.out.println("初始化conn");
                String user = "root";
                String psw = "123456";
                String url = "jdbc:mysql://192.168.80.128:3306/centos";
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, psw);
                long endTime = System.currentTimeMillis();
                if (null != conn) {
                    logger.info("获取数据库连接成功,耗时:{}ms", String.valueOf(endTime - startTime));
                }else{
                    logger.info("获取数据库连接失败,耗时:{}ms", String.valueOf(endTime - startTime));
                }
            } else {
                System.out.println("已存在实例");
            }
        } catch (Exception ex) {
            logger.error("连接异常:", ex);
        }
        return conn;
    }

    public static DBRedisUser queryByUsername(String usernameStr) {
        System.out.println("查询username:" + usernameStr);
        DBRedisUser user = new DBRedisUser();
        long timeBefore = System.currentTimeMillis();
        if (!RedisUtils.isExistData()) {
            String sql = "SELECT id,username,age,address,phoneNo FROM tb_user WHERE username='" + usernameStr + "'";
            try {
                PreparedStatement pstmt = getConnect().prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                int id = 0;
                String username = "";
                int age = 0;
                String address = "";
                String phoneNo = "";
                while (rs.next()) {
                    id = rs.getInt(1);
                    username = rs.getString(2);
                    age = rs.getInt(3);
                    address = rs.getString(4);
                    phoneNo = rs.getString(5);
                    user.setAddress(address);
                    user.setAge(age);
                    user.setId(id);
                    user.setPhoneNo(phoneNo);
                    user.setUsername(username);

//					RedisUtils.setRedisData(user);
                    System.out.println("查询到的记录为：" + id + "," + username + "," + age + "," + address + "," + phoneNo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
//			RedisUtils.getRedisData();
        }
        System.out.println("用时：" + (System.currentTimeMillis() - timeBefore));
        return user;
    }

    // cap_repayment_detail_sync 批量插入数据
    public static void insertBatch(int count) throws SQLException {
        String[] status = {"B00", "C00", "A00", "S00", "S01", "S02", "A01"};
        String[] capitals = {"BSB", "BEE", "CRB"};
        int[] periods = {3, 6, 12};

        for (int row = 0; row <= count; row++) {
            int totalPeriod = periods[row % periods.length];
            String loanId = generateSequenceNo();
            for (int i = 1; i <= totalPeriod; i++) {
                String sql = "INSERT INTO cap_repayment_detail_sync (ID,CAPITAL_CODE,LOAN_ID,TOTAL_PERIOD,PERIOD,STATUS)VALUES('" + generateSequenceNo() + "', '" + capitals[i % capitals.length] + "','" + loanId + "','" + totalPeriod + "','" + i + "','" + status[i % status.length] + "')";
                PreparedStatement pstmt = getConnect().prepareStatement(sql);
                System.out.println("insert status:" + pstmt.execute());
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        // System.out.println(getConnect().toString());
        // try {
        // Thread.sleep(2000);
        // System.out.println(getConnect().toString());
        // } catch (InterruptedException e) {
        // }

        // System.out.println(queryByUsername("user_972").getId());
        // for (int i = 0; i < 1000; i++) {
        // generateSequenceNo();
        // }

        insertBatch(999999);
    }

    /**
     * The FieldPosition.
     */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /**
     * This Format for format the data to special format.
     */
    private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");

    /**
     * This Format for format the number to special format.
     */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");

    /**
     * This int is the sequence number ,the default value is 0.
     */
    private static int seq = 0;

    private static final int MAX = 9999;

    public static String generateSequenceNo() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }

        System.out.println("id:" + sb.toString());
        return sb.toString();
    }
}
