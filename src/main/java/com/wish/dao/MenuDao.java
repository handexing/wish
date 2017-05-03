package com.wish.dao;

import com.wish.entity.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月19日 下午3:43:14
 */

@Repository
public interface MenuDao extends JpaRepository<Menu, Long> {

	@Query(nativeQuery = true, value = "select * from menu where id in"
			+ "(select menu_id from role_menu where role_Id = :roleId)")
	public List<Menu> findMenuByRoleId(@Param("roleId") Long roleId);

	@Query(nativeQuery = true, value = "select * from menu where id in"
			+ "(select menu_id from role_menu where role_Id = "
			+ "(select role_Id from role_user where user_id = :userId))")
	public List<Menu> findMenuByUserId(@Param("userId") Long userId);

}
