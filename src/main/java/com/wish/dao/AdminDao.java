package com.wish.dao;

import com.wish.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月28日 上午11:05:35
 */

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {

}
