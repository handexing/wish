package com.wish.dao;

import com.wish.entity.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月28日 上午11:05:35
 */

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {

	Page<Article> findByTitle(String title, Pageable pageable);

}
