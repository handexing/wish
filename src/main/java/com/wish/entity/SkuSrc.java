package com.wish.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pinky Lam 908716835@qq.com
 * @date 2017年7月24日 下午4:25:14
 */
@Entity
@Table(name = "SKU_SRC")
public class SkuSrc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "SKU_CODE")
	private String skuCode;
	@Column(name = "PLATFM_CODE")
	private Integer platfmCode;
	@Column(name = "URL")
	private String url;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public Long getId() {
		return id;
	}

	public Integer getPlatfmCode() {
		return platfmCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public String getUrl() {
		return url;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPlatfmCode(Integer platfmCode) {
		this.platfmCode = platfmCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "SkuSrc [id=" + id + ", skuCode=" + skuCode + ", platfmCode=" + platfmCode + ", url=" + url
				+ ", createTime=" + createTime + "]";
	}


}
