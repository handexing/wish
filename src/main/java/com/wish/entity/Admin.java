package com.wish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PSW")
	private String psw;

	public Admin() {
		super();
	}

	public Admin(String name, String psw) {
		super();
		this.name = name;
		this.psw = psw;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPsw() {
		return psw;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", psw=" + psw + "]";
	}

}
