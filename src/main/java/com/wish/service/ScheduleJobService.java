package com.wish.service;

import com.wish.dao.ScheduleJobDao;
import com.wish.entity.ScheduleJob;
import com.wish.quartz.QuartzJobFactory;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class ScheduleJobService {

	@Autowired
	ScheduleJobDao scheduleJobDao;
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addJob(ScheduleJob job) throws SchedulerException {
		if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
			return;
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//在这里把它设计成一个Job对应一个trigger，两者的分组及名称相同，方便管理，条理也比较清晰，在创建任务时如果不存在新建一个，如果已经存在则更新任务
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
					.withIdentity(job.getJobName(), job.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	@Transactional
	public void addTask(ScheduleJob job) {
		job.setCreateTime(new Date());
		scheduleJobDao.save(job);
	}

	/**
	 * 更改任务状态
	 * 
	 * @throws SchedulerException
	 */
	@Transactional
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {

		ScheduleJob job = scheduleJobDao.findOne(jobId);
		if (job == null) {
			return;
		}
		if ("stop".equals(cmd)) {
			deleteJob(job);
			job.setJobStatus(ScheduleJob.STOP_RUNNING);
		} else if ("start".equals(cmd)) {
			job.setJobStatus(ScheduleJob.STATUS_RUNNING);
			addJob(job);
		}
		job.setUpdateTime(new Date());
		scheduleJobDao.save(job);
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);
	}

	/**
	 * @Title: initScheduler 
	 * @Description:初始化定时任务
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public void initScheduler() {
		try {
			List<ScheduleJob> list = scheduleJobDao.findAll();
			for (ScheduleJob scheduleJob : list) {
				if ("1".equals(scheduleJob.getJobStatus())) {
					addJob(scheduleJob);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
	public void updateCron(Long jobId, String cron) throws SchedulerException {
		ScheduleJob job = scheduleJobDao.findOne(jobId);
		if (job == null) {
			return;
		}
		job.setCronExpression(cron);
		if (ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
			updateJobCron(job);
		}
		job.setUpdateTime(new Date());
		scheduleJobDao.save(job);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

}
