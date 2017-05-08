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
 * @author handx 908716835@qq.com
 * @date 2017年5月7日 下午5:29:45
 */

@Entity
@Table(name = "SCHEDULE_JOB")
public class ScheduleJob {

	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JOB_ID")
	private Long jobId;
	@Column(name = "JOB_NAME")
	private String jobName;// 任务名称
	@Column(name = "JOB_GROUP")
	private String jobGroup;// 任务分组
	@Column(name = "JOB_STATUS")
	private String jobStatus;// 任务状态 是否启动任务
	@Column(name = "CRON_EXPRESSION")
	private String cronExpression;// cron表达式
	@Column(name = "DESCRIPTION")
	private String description;// 描述
	@Column(name = "BEAN_CLASS")
	private String beanClass;// 任务执行时调用哪个类的方法 包名+类名
	@Column(name = "IS_CONCURRENT")
	private String isConcurrent;// 任务是否有状态
	@Column(name = "SPRING_ID")
	private String springId;// spring bean
	@Column(name = "METHOD_NAME")
	private String methodName;// 任务调用的方法名
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	public String getBeanClass() {
		return beanClass;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public Long getJobId() {
		return jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getSpringId() {
		return springId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ScheduleJob [jobId=" + jobId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", jobStatus="
				+ jobStatus + ", cronExpression=" + cronExpression + ", description=" + description + ", beanClass="
				+ beanClass + ", isConcurrent=" + isConcurrent + ", springId=" + springId + ", methodName=" + methodName
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}