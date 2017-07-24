package com.wish.dao;

import com.wish.entity.SkuSrc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pinky Lam 908716835@qq.com
 * @date 2017年7月24日 下午4:29:06
 */

@Repository
public interface SkuSrcDao extends JpaRepository<SkuSrc, Long> {

}
