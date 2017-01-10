package com.yonon.demo.redis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.yonon.demo.domain.DBRedisUser;

/**
 * @author JiangYinghan 2017年1月4日
 *
 */
public class DBUtils {
	private static Connection conn = null;

	public static Connection getConnect() {
		if (conn == null) {
			System.out.println("初始化conn");
			String user = "root";
			String psw = "123456";
			String url = "jdbc:mysql://192.168.174.128:3306/common";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, psw);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("已存在实例");
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

					RedisUtils.setRedisData(user);
					System.out.println("查询到的记录为：" + id + "," + username + "," + age + "," + address + "," + phoneNo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			RedisUtils.getRedisData();
		}
		System.out.println("用时：" + (System.currentTimeMillis() - timeBefore));
		return user;
	}

	public static void main(String[] args) {
		// System.out.println(getConnect().toString());
		// try {
		// Thread.sleep(2000);
		// System.out.println(getConnect().toString());
		// } catch (InterruptedException e) {
		// }
		System.out.println(queryByUsername("user_972").getId());
	}
}
