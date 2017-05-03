package com.wish.dao;

import com.wish.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月19日 下午3:43:14
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {

	Page<User> findByAccount(String account, Pageable pageable);

	public User findByAccountAndPassword(String account, String password);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE user SET NAME=:name WHERE ID =:id")
	int updateUser(@Param("id") Long id, @Param("name") String name);

}

