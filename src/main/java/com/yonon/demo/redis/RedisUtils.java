package com.yonon.demo.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yonon.demo.domain.DBRedisUser;

import redis.clients.jedis.Jedis;

/**
 * @author JiangYinghan 2017年1月4日
 *
 */
public class RedisUtils {

	private static Jedis jedis;

	public static Jedis getConnect() {
		if (jedis == null) {
			System.out.println("初始化redis");
			System.out.println("开始获取连接");
			jedis = new Jedis("192.168.174.128", 6379);
			System.out.println("获取连接结果：" + jedis.ping());
		} else {
			System.out.println("已存在redis实例");
		}
		return jedis;
	}

	public static void main(String[] args) {
		getConnect();
		try {
			Thread.sleep(5000);
			getConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void setRedisData(DBRedisUser user) {
		getConnect();
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("id", String.valueOf(user.getId()));
		userMap.put("username", user.getUsername());
		userMap.put("age", String.valueOf(user.getAge()));
		userMap.put("address", user.getAddress());
		userMap.put("phoneNo", user.getPhoneNo());
		jedis.hmset("user", userMap);
	}

	public static boolean isExistData() {
		getConnect();
		if (jedis.exists("user"))
			return true;
		else
			return false;
	}

	public static void getRedisData() {
		DBRedisUser user = new DBRedisUser();
		List<String> rsmap = jedis.hmget("user", "id", "username", "age", "address", "phoneNo");
		System.out.println(rsmap.toString());
		System.out.println(rsmap.get(2));
		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("user", key));
		}
	}
}
