package com.yonon.demo.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yonon.demo.dao.SpringSrcUserDao;
import com.yonon.demo.domain.SpringSrcUser;

/**
 * @author JiangYinghan 2017年1月10日
 *
 */
public class SpringSrcUserDaoTest {
	@Test
	public void test() {
		SpringSrcUser user = new SpringSrcUser();
		user.setUsername("testUsername");
		ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		SpringSrcUserDao springSrcUserDao = (SpringSrcUserDao) ac.getBean("springSrcUserDao");

		springSrcUserDao.addUser(user);
	}
}
