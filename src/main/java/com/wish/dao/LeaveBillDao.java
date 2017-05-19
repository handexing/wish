package com.wish.dao;

import com.wish.entity.LeaveBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月17日 下午4:17:14
 */

@Repository
public interface LeaveBillDao extends JpaRepository<LeaveBill, Long> {

	List<LeaveBill> findLeaveBillByState(Integer state);

}
