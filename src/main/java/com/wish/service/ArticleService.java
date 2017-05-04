package com.wish.service;

import com.wish.dao.ArticleDao;
import com.wish.entity.Article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月28日 下午4:51:06
 */

@Service
public class ArticleService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ArticleDao articleDao;

	@Transactional
	public void saveArticle(String title, String content, Integer status, Long id) {
		Article article = new Article();
		article.setContent(content);
		article.setStatus(status);
		article.setTitle(title);
		if (id != null) {
			Article article2 = articleDao.findOne(id);
			article.setId(id);
			article.setUpdateTime(new Date());
			article.setCreateTime(article2.getCreateTime());
		} else {
			article.setCreateTime(new Date());
		}
		articleDao.save(article);
	}

}
