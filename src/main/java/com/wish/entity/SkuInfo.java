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
 * @date 2017年7月24日 下午4:27:45
 */
@Entity
@Table(name = "SKU_INFO")
public class SkuInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "SKU_SRC_ID")
	private Long skuSrcId;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "SUBTITLE")
	private String subtitle;
	@Column(name = "PRICE")
	private Double price;
	@DateTimeFormat(pattern = "yyyyMMdd")
	@JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
	@Column(name = "DATE_ID")
	private Date dateId;

	public Date getDateId() {
		return dateId;
	}

	public Long getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public Long getSkuSrcId() {
		return skuSrcId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setDateId(Date dateId) {
		this.dateId = dateId;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setSkuSrcId(Long skuSrcId) {
		this.skuSrcId = skuSrcId;
	}



	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SkuInfo [id=" + id + ", skuSrcId=" + skuSrcId + ", title=" + title + ", subtitle=" + subtitle
				+ ", price=" + price + ", dateId=" + dateId + "]";
	}



}
