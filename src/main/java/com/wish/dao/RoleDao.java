package com.wish.dao;

import com.wish.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月24日 上午9:57:24
 */

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

}
