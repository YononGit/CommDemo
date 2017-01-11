package com.yonon.demo.dao.impl;

import org.springframework.stereotype.Component;

import com.yonon.demo.dao.SpringSrcUserDao;
import com.yonon.demo.domain.SpringSrcUser;

/**
 * @author JiangYinghan 2017年1月10日
 *
 */
@Component("springSrcUserDao")
public class SpringSrcUserDaoImpl implements SpringSrcUserDao {

	public void addUser(SpringSrcUser user) {
		System.out.println("user:" + user.getUsername() + " is add now");
	}

}
