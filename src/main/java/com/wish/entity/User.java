package com.wish.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月27日 下午4:41:52
 */

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ACCOUNT")
    private String account;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "STATUS")
    private Integer status;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CREATE_TIME")
    private Date createTime;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ROLE_USER", joinColumns = @JoinColumn(name = "ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	RoleUser roleUser;

	public String getAccount() {
        return account;
    }

	public Date getCreateTime() {
        return createTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public RoleUser getRoleUser() {
		return roleUser;
	}

    public Integer getStatus() {
        return status;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public void setRoleUser(RoleUser roleUser) {
		this.roleUser = roleUser;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
