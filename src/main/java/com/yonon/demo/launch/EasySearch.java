package com.yonon.demo.launch;

import java.util.Scanner;

import com.yonon.demo.redis.DBUtils;

/**
 * @author JiangYinghan 2017年1月10日
 *
 */
public class EasySearch {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String input = in.nextLine();
			if ("query".equals(input))
				System.out.println(DBUtils.queryByUsername("user_972").getId());
			else{
				break;
			}
		}
		System.out.println("process end...");
	}
}
