package com.wish.dao;

import com.wish.entity.RoleMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月25日 上午10:18:28
 */

@Repository
public interface RoleMenuDao extends JpaRepository<RoleMenu, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM role_menu WHERE MENU_ID =:id")
	int deleteRoleMenu(@Param("id") Long id);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM role_menu WHERE ROLE_ID =:roleId")
	int deleteRoleMenuByRoleId(@Param("roleId") Long roleId);

	RoleMenu findByRoleIdAndMenuId(Long roleId, Long menuId);

	RoleMenu findRoleMenuByMenuId(Long menuId);

}
