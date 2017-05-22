package com.wish.controller;

import com.wish.entity.User;
import com.wish.model.ActReProcdef;
import com.wish.model.ExecuteResult;
import com.wish.model.MyProcessInstance;
import com.wish.model.RetJson;
import com.wish.model.Task;
import com.wish.service.ProcessService;
import com.wish.util.BpmnUtil;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

/**
 * @Description: 工作流管理
 * @author handx 908716835@qq.com
 * @date 2017年5月16日 下午9:50:29
 *
 */

@RestController
@RequestMapping("process")
public class ProcessController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProcessEngine processEngine;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;
	@Autowired
	ManagementService managementService;
	@Autowired
	BpmnUtil bpmnUtil;
	@Autowired
	ProcessService processService;

	@RequestMapping(value = "cancelDeploy", method = RequestMethod.POST)
	public void cancelDeploy(HttpSession session, String uuid) {
		File file = new File(uuid);
		file.delete();
		session.removeAttribute(uuid);
	}

	@RequestMapping(value = "checkBpmn", method = RequestMethod.POST)
	public Map<String, Object> checkBpmn(@RequestParam MultipartFile file, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = false;
		if (!file.isEmpty()) {
			String bpmnKey = bpmnUtil.getBpmnKey(file.getInputStream());

			List<ProcessDefinition> definitionList = repositoryService.createProcessDefinitionQuery()
					.processDefinitionKey(bpmnKey).list();
			if (!CollectionUtils.isEmpty(definitionList)) {
				String uuid = UUID.randomUUID().toString();
				session.setAttribute("fileName", file.getOriginalFilename());
				File tmpfile = new File(uuid);
				FileOutputStream fos = new FileOutputStream(tmpfile);
				IOUtils.copy(file.getInputStream(), fos);
				fos.flush();
				fos.close();
				map.put("uuid", uuid);
				isExist = true;
			} else {
				RepositoryService service = processEngine.getRepositoryService();
				service.createDeployment().addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
			}
			map.put("isExist", isExist);
		}
		return map;
	}

	@RequestMapping(value = "delProcess")
	public ExecuteResult<Boolean> delProcess(String id) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			processEngine.getRepositoryService()
					// 普通删除,删除没有在执行的流程,如果流程正在执行,则抛出异常
					// .deleteDeployment(deploymentId);
					// 级联删除,不管你在不在运行,会删除当前关联的所有信息,包括在历史表里的数据
					.deleteDeployment(id, true);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping(value = "deployProcess", method = RequestMethod.POST)
	public ExecuteResult<Boolean> deploy(HttpSession session, String uuid) throws IOException {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			File file = new File(uuid);
			if (file.exists()) {
				RepositoryService service = processEngine.getRepositoryService();
				FileInputStream fis = new FileInputStream(file);
				service.createDeployment().addInputStream((String) session.getAttribute("fileName"), fis).deploy();
				fis.close();
				file.deleteOnExit();
				session.removeAttribute(uuid);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping(value = "getBpmnSource", produces = "text/html;charset=UTF-8")
	public String getBpmnSource(String id) throws IOException {
		InputStream inputStream = repositoryService.getProcessModel(id);
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer);
		return writer.toString();
	}

	@RequestMapping("listProcess")
	public RetJson listProcess(Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		List<ProcessDefinition> listPage = new ArrayList<ProcessDefinition>();
		List<ActReProcdef> list = new ArrayList<ActReProcdef>();
		try {
			ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().latestVersion();
			long count = query.count();
			listPage = query.listPage(start, length);
			for (ProcessDefinition p : listPage) {
				ActReProcdef a = new ActReProcdef();
				BeanUtils.copyProperties(a, p);
				list.add(a);
			}
			retJson.setData(list);
			retJson.setRecordsTotal(count);
			retJson.setRecordsFiltered(count);
			retJson.setDraw(draw == null ? 0 : draw);
		} catch (Exception e) {
			logger.error("获取流程列表异常", e);
		}
		return retJson;
	}

	@RequestMapping("listTask")
	public RetJson listTask(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Task> list = new ArrayList<Task>();
		RetJson retJson = new RetJson();
		List<org.activiti.engine.task.Task> listPage = taskService.createTaskQuery().taskAssignee(user.getAccount())
				.orderByTaskCreateTime().asc().list();
		try {
			for (org.activiti.engine.task.Task task : listPage) {
				Task a = new Task();
				BeanUtils.copyProperties(a, task);
				list.add(a);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		retJson.setData(list);
		return retJson;
	}

	@RequestMapping("runningProcessList")
	public RetJson runningProcessList(Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		List<MyProcessInstance> list = new ArrayList<MyProcessInstance>();
		try {
			ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
			long count = query.count();
			List<ProcessInstance> listPage = query.listPage(start, length);
			for (ProcessInstance processInstance : listPage) {
				MyProcessInstance a = new MyProcessInstance();
				BeanUtils.copyProperties(a, processInstance);
				list.add(a);
			}
			retJson.setData(list);
			retJson.setRecordsTotal(count);
			retJson.setRecordsFiltered(count);
			retJson.setDraw(draw == null ? 0 : draw);
		} catch (Exception e) {
			logger.error("获取运行中流程列表异常", e);
		}
		return retJson;
	}

	@RequestMapping("processPage")
	public ModelAndView showProcessPage() {
		return new ModelAndView("/activiti/processList");
	}

	@RequestMapping("runningProcessPage")
	public ModelAndView showRunningProcessPage() {
		return new ModelAndView("/activiti/runningProcessList");
	}

	@RequestMapping("taskProcessPage")
	public ModelAndView showTaskProcessPage() {
		return new ModelAndView("/activiti/taskList");
	}
}
