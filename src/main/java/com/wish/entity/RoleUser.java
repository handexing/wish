package com.wish.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wish.util.CustomDateSerializer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月25日 上午11:07:36
 */

@Entity
@Table(name = "ROLE_USER")
public class RoleUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ROLE_ID")
	private Long roleId;
	@Column(name = "USER_ID")
	private Long userId;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public Long getId() {
		return id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "RoleUser [id=" + id + ", roleId=" + roleId + ", userId=" + userId + ", createTime=" + createTime + "]";
	}


}
