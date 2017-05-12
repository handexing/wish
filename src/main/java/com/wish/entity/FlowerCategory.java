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
 * @Description: 花的品类
 * @author handx 908716835@qq.com
 * @date 2017年5月12日 上午10:11:55
 *
 */

@Entity
@Table(name = "FLOWER_CATEGORY")
public class FlowerCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "URL")
	private String url;
	@Column(name = "IMGPATH")
	private String imgPath;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public String getImgPath() {
		return imgPath;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "FlowerCategory [id=" + id + ", name=" + name + ", url=" + url + ", imgPath=" + imgPath + ", createTime="
				+ createTime + "]";
	}

}
