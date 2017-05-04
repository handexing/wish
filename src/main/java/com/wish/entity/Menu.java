package com.wish.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author handx 908716835@qq.com
 * @date 2017年4月19日 下午3:36:13
 */

@Entity
@Table(name = "MENU")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	@Column(name = "PID")
    private Long pid;
	@Column(name = "TITLE")
    private String title;
	@Column(name = "HREF")
	private String href;
	@Column(name = "ICON")
	private String icon;
	@Column(name = "STATUS")
    private Integer status;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "CREATE_TIME")
    private Date createTime;

	@Transient
	private List<Menu> children;

	@Transient
	private String text;
	@Transient
	private Map<String, Boolean> state;

	public List<Menu> getChildren() {
		return children;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getHref() {
		return href;
	}

	public String getIcon() {
		return icon;
	}

	public Long getId() {
		return id;
	}

	public Long getPid() {
		return pid;
	}

	public Object getState() {
		return state;
	}

	public Integer getStatus() {
        return status;
    }

	public String getText() {
		return text;
	}

	public String getTitle() {
        return title;
    }

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public void setHref(String href) {
		this.href = href;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setState(Map<String, Boolean> state) {
		this.state = state;
	}

    public void setStatus(Integer status) {
		this.status = status;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTitle(String title) {
        this.title = title;
    }

	@Override
	public String toString() {
		return "CmsMenu [id=" + id + ", pid=" + pid + ", title=" + title + ", href=" + href + ", icon=" + icon
				+ ", status=" + status + ", createTime=" + createTime + ", children=" + children + ", text=" + text
				+ ", state=" + state + "]";
	}

}
