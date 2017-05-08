package com.wish.dao;

import com.wish.entity.ScheduleJob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月8日 上午10:15:44
 */

@Repository
public interface ScheduleJobDao extends JpaRepository<ScheduleJob, Long> {

}
