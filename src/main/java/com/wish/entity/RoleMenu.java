package com.wish.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月25日 上午10:16:17
 */

@Entity
@Table(name = "ROLE_MENU")
public class RoleMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ROLE_ID")
	private Long roleId;
	@Column(name = "MENU_ID")
    private Long menuId;
	@Column(name = "CREATE_TIME")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

	public Long getId() {
		return id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public Long getRoleId() {
		return roleId;
	}

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "RoleMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + ", createTime=" + createTime + "]";
	}


}
