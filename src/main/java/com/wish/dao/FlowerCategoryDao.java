package com.wish.dao;

import com.wish.entity.FlowerCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月12日 上午10:12:17
 */

@Repository
public interface FlowerCategoryDao extends JpaRepository<FlowerCategory, Long> {

}
