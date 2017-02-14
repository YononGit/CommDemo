package com.yonon.demo.launch;

/**
 * @author JiangYinghan 2017年2月5日
 *
 */
public class DebugDemo {
	public static void main(String[] args) {

		System.out.println("debug... start");
		System.out.println("debug... add:" + add(10, 20));
		System.out.println("debug... end");
	}

	public static int add(int a, int b) {
		return a + b;
	}
}
