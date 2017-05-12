package com.wish.dao.test;

import com.wish.dao.FlowerCategoryDao;
import com.wish.entity.FlowerCategory;
import com.wish.util.JsoupUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring.xml")
@ActiveProfiles("test")
public class FlowerCategoryDaoTest {

	@Autowired
	FlowerCategoryDao flowerCategoryDao;

	@Test
	public void testSaveCategory() {
		List<FlowerCategory> categoryList = JsoupUtils.getCategoryList();
		flowerCategoryDao.save(categoryList);
	}
}
