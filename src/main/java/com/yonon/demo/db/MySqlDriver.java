package com.yonon.demo.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by jr-jiangyinghan on 2017/6/20.
 */
public class MySqlDriver {
    private static final String url = "jdbc:mysql://192.168.80.128:3306/centos";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "123456";

    private static Connection conn = null;
    private static PreparedStatement pst = null;


    private static Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("获取conn...");
                Class.forName(name);//指定连接类型
                conn = DriverManager.getConnection(url, user, password);//获取连接
            } else {
                System.out.println("缓存conn...");
            }
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ResultSet getRS(String sql) {
        Connection con = getConn();
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sql);//准备执行语句
            System.out.println(sql + "\n" + "执行完毕..");
            rs = pst.executeQuery();
//           while(rs.next()){
//               String id = rs.getString(0);
//               String name = rs.getString(1);
//               String sex = rs.getString(2);
//           }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

}
