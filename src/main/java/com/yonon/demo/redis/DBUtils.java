package com.yonon.demo.redis;

import com.yonon.demo.domain.DBRedisUser;
import com.yonon.demo.domain.Student;
import com.yonon.demo.factory.StudentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

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
                } else {
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
//    public static void insertBatch(int count) throws SQLException {
//        String[] status = {"B00", "C00", "A00", "S00", "S01", "S02", "A01"};
//        String[] capitals = {"BSB", "BEE", "CRB"};
//        int[] periods = {3, 6, 12};
//
//        for (int row = 0; row <= count; row++) {
//            int totalPeriod = periods[row % periods.length];
//            String loanId = generateSequenceNo();
//            for (int i = 1; i <= totalPeriod; i++) {
//                String sql = "INSERT INTO cap_repayment_detail_sync (ID,CAPITAL_CODE,LOAN_ID,TOTAL_PERIOD,PERIOD,STATUS)VALUES('" + generateSequenceNo() + "', '" + capitals[i % capitals.length] + "','" + loanId + "','" + totalPeriod + "','" + i + "','" + status[i % status.length] + "')";
//                PreparedStatement pstmt = getConnect().prepareStatement(sql);
//                System.out.println("insert status:" + pstmt.execute());
//            }
//        }
//    }

    public static void main(String[] args) {
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

//        insertBatch(999999);
//        for (int i = 0; i < 1000; i++) {
//            logger.info(StudentFactory.genRandName());
//        }
        try {
            insertStdns(500000);
        } catch (Exception ex) {
            logger.info("insert ex:", ex);
        }
    }


    //
    public static void insertStdns(int index) throws Exception {
        for (int i = 0; i < index; i++) {
            String id;
            String name;
            String sex;
            int age;
            String dept;
            id = StudentFactory.generateSequenceNo();
            name = StudentFactory.genRandName();
            sex = StudentFactory.getSex(i);
            age = (i + 1) % 50 + 30;
            dept = "auto-user-info[" + id + "," + name + "," + sex + "," + age + "]";
            logger.info("auto-create user complete!");
            logger.info("user-id:{},name:{},sex:{},age:{},dept:{}", id, name, sex, age, dept);
            String sql = "INSERT INTO student(Sno,Sname,Ssex,Sage,Sdept)values('" + id + "','" + name + "','" + sex + "','" + age + "','" + dept + "'" + ")";
            logger.info("ready sql-->{}", sql);
            PreparedStatement pstmt = getConnect().prepareStatement(sql);
            pstmt.execute();
            logger.info("save data successful ! process--> {}", StudentFactory.process(index, i + 1));
            // threadInsert(i + 1);
        }
    }

    public static void threadInsert(int index) throws Exception {
        logger.info("insert start to sleep");
        if (index % 20000 == 0)
            Thread.sleep(1000);
        logger.info("insert start to work");
    }

    public static ArrayList<Student> queryBySql(String sql) {
        ArrayList<Student> studentList = new ArrayList();
        long timeBefore = System.currentTimeMillis();
        try {
            PreparedStatement pstmt = getConnect().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String id;
                String name;
                String sex;
                int age;
                String dept;
                id = rs.getString(1);
                name = rs.getString(2);
                sex = rs.getString(3);
                age = rs.getInt(4);
                dept = rs.getString(5);
                Student student = new Student(id, name, sex, age, dept);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("queryBySql用时：" + (System.currentTimeMillis() - timeBefore) + "ms");
        return studentList;
    }

    public static int queryCount(String sql) {
        long timeBefore = System.currentTimeMillis();
        int count = 0;
        try {
            PreparedStatement pstmt = getConnect().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("queryCount用时：" + (System.currentTimeMillis() - timeBefore) + "ms");
        return count;
    }

}
