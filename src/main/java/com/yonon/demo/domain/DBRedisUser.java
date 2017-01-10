package com.yonon.demo.domain;

/**
 * @author JiangYinghan 2017年1月6日
 *
 */
public class DBRedisUser {
	private String address;
	private int age;
	private int id;
	private String phoneNo;
	private String username;

	public DBRedisUser() {
		super();
	}

	/**
	 * @param id
	 * @param username
	 * @param age
	 * @param address
	 * @param phoneNo
	 */
	public DBRedisUser(int id, String username, int age, String address, String phoneNo) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public int getAge() {
		return age;
	}

	public int getId() {
		return id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getUsername() {
		return username;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
