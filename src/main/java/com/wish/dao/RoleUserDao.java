package com.wish.dao;

import com.wish.entity.RoleUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月25日 下午2:04:04
 */

@Repository
public interface RoleUserDao extends JpaRepository<RoleUser, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE role_user SET ROLE_ID=:roleId WHERE USER_ID =:id")
	int updateGroupUser(@Param("roleId") Long roleId, @Param("id") Long id);

}

