package com.wish.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @ClassName: LeaveBill
 * @Description: 请假流程实体
 * @author handx 908716835@qq.com
 * @date 2017年5月17日 下午9:46:05
 *
 */

@Entity
@Table(name = "LEAVE_BILL")
public class LeaveBill implements Serializable{

	private static final long serialVersionUID = 2000001315172295755L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "DAYS")
	private Integer days;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "REMARK")
	private String remark;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Column(name = "LEAVE_DATE")
	private Date leaveDate;
	@Column(name = "STATE")
	private Integer state;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	User user;

	public String getContent() {
		return content;
	}

	public Integer getDays() {
		return days;
	}

	public Long getId() {
		return id;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getState() {
		return state;
	}

	public User getUser() {
		return user;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Leavebill [id=" + id + ", days=" + days + ", content=" + content + ", remark=" + remark + ", leaveDate="
				+ leaveDate + ", state=" + state + ", user=" + user + "]";
	}

}
