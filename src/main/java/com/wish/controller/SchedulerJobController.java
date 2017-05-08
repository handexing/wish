package com.wish.controller;

import com.wish.dao.ScheduleJobDao;
import com.wish.entity.ScheduleJob;
import com.wish.model.ExecuteResult;
import com.wish.model.RetJson;
import com.wish.service.ScheduleJobService;
import com.wish.util.PageUtil;
import com.wish.util.SpringUtils;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 任务调度
 * @author handx 908716835@qq.com
 * @date 2017年5月6日 下午1:23:32
 */

@RestController
@RequestMapping("job")
public class SchedulerJobController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ScheduleJobDao scheduleJobDao;
	@Autowired
	ScheduleJobService scheduleJobService;

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping("add")
	public ExecuteResult<Boolean> add(@RequestBody ScheduleJob scheduleJob) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		result.setSuccess(false);
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		} catch (Exception e) {
			result.setErrorMsg("cron表达式有误，不能被解析！");
			return result;
		}

		Object obj = null;
		try {
			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
				obj = SpringUtils.getBean(scheduleJob.getSpringId());
			} else {
				Class clazz = Class.forName(scheduleJob.getBeanClass());
				obj = clazz.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (obj == null) {
			result.setErrorMsg("未找到目标类！");
			return result;
		} else {
			Class clazz = obj.getClass();
			Method method = null;
			try {
				method = clazz.getMethod(scheduleJob.getMethodName(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (method == null) {
				result.setErrorMsg("未找到目标方法！");
				return result;
			}
		}

		try {
			scheduleJobService.addTask(scheduleJob);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMsg("保存失败，检查 name group 组合是否有重复！");
		}
		return result;
	}

	@RequestMapping("changeJobStatus")
	@ResponseBody
	public ExecuteResult<Boolean> changeJobStatus(Long jobId, String cmd) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			scheduleJobService.changeStatus(jobId, cmd);
			result.setSuccess(true);
		} catch (SchedulerException e) {
			result.setSuccess(false);
			logger.error("", e);
			result.setErrorMsg("任务状态改变失败！");
		}
		return result;
	}

	@RequestMapping("jobList")
	public RetJson jobList(Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		final Sort sort = new Sort(Sort.Direction.DESC, "jobId");
		final Pageable pageRequest = new PageRequest(PageUtil.calcPage(start), length, sort);
		Page<ScheduleJob> pageData = scheduleJobDao.findAll(pageRequest);
		List<ScheduleJob> jobList = pageData.getContent();
		try {
			for (ScheduleJob job : jobList) {
				if ("1".equals(job.getJobStatus())) {
					scheduleJobService.addJob(job);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		retJson.setData(pageData.getContent());
		retJson.setRecordsTotal(pageData.getTotalElements());
		retJson.setRecordsFiltered(pageData.getTotalElements());
		retJson.setDraw(draw == null ? 0 : draw);
		return retJson;
	}

	@RequestMapping("jobPage")
	public ModelAndView showArticlePage() {
		return new ModelAndView("/job/jobList");
	}

	@RequestMapping("updateCron")
	@ResponseBody
	public ExecuteResult<Boolean> updateCron(HttpServletRequest request, Long jobId, String cron) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
			result.setErrorMsg("cron表达式有误，不能被解析！");
			return result;
		}
		try {
			scheduleJobService.updateCron(jobId, cron);
			result.setSuccess(true);
		} catch (SchedulerException e) {
			result.setSuccess(false);
			result.setErrorMsg("cron更新失败！");
			logger.error("", e);
		}
		return result;
	}

}
