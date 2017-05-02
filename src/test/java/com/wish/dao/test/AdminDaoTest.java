package com.wish.dao.test;

import com.wish.dao.AdminDao;
import com.wish.entity.Admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
@ActiveProfiles("test")
public class AdminDaoTest {

	@Autowired
	AdminDao adminDao;

	@Test
	public void testSaveAdmin() {

		Admin admin = new Admin("小强", "123456");
		adminDao.save(admin);

	}
}
