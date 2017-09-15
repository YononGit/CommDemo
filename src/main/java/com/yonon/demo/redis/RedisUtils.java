package com.yonon.demo.redis;

import com.yonon.demo.domain.Student;
import com.yonon.demo.util.SerializableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author JiangYinghan 2017年1月4日
 */
public class RedisUtils {

    private static Jedis jedis;
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    public static synchronized Jedis getConnect() {
        if (jedis == null) {
            System.out.println("初始化redis");
            System.out.println("开始获取连接");
            jedis = new Jedis("192.168.80.128", 6379);
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
        jedis.set("yonon", "ma");
    }

//	public static void setRedisData(DBRedisUser user) {
//		getConnect();
//		Map<String, String> userMap = new HashMap<String, String>();
//		userMap.put("id", String.valueOf(user.getId()));
//		userMap.put("username", user.getUsername());
//		userMap.put("age", String.valueOf(user.getAge()));
//		userMap.put("address", user.getAddress());
//		userMap.put("phoneNo", user.getPhoneNo());
//		jedis.hmset("user", userMap);
//	}

    public static boolean isExistData() {
        getConnect();
        if (jedis.exists("user"))
            return true;
        else
            return false;
    }

//	public static void getRedisData(String key) {
//		DBRedisUser user = new DBRedisUser();
//		List<String> rsmap = jedis.hmget("user", "id", "username", "age", "address", "phoneNo");
//		System.out.println(rsmap.toString());
//		System.out.println(rsmap.get(2));
//		Iterator<String> iter = jedis.hkeys("user").iterator();
//		while (iter.hasNext()) {
//			String key = iter.next();
//			System.out.println(key + ":" + jedis.hmget("user", key));
//			return key;
//		}
//		return "error"
//	}

    public static void setRedisData(String key, String value) {
        getConnect();
        jedis.set(key, value);
        System.out.println("key:" + key + "_value:" + value + " set...");
    }

    public static String getRedisData(String key) {
        getConnect();
        System.out.println("key:" + key);
        String value = jedis.get(key);
        System.out.println("get value:" + value);
        return value;
    }

    public static void setStudentObject(Student student, String uniqueStmp, int index) {
        getConnect();
        logger.info("add to redis start,id:{}", student.getId());
        jedis.set(uniqueStmp.concat(String.valueOf(index)).getBytes(), SerializableUtils.serialize(student));
        logger.info("add to redis finish,id:{}", student.getId());
    }

    public static Student getStudentObject(String uniqueStmp, int index) {
        getConnect();
        byte[] person = jedis.get(uniqueStmp.concat(String.valueOf(index)).getBytes());
        return (Student) SerializableUtils.unserialize(person);
    }

}
