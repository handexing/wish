package com.wish.quartz;

import com.wish.entity.ScheduleJob;
import com.wish.util.TaskUtils;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description: 计划任务执行处 无状态
 * @author handx 908716835@qq.com
 * @date 2017年5月7日 下午10:04:55
 *
 */
public class QuartzJobFactory implements Job {

	public final Logger log = Logger.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}