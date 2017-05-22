package com.wish.model;

import java.util.Map;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月22日 下午4:17:13
 */

public class Task {

	/** DB id of the task. */
	String id;

	/**
	 * Name or title of the task.
	 */
	String name;

	/**
	 * Free text description of the task.
	 */
	String description;

	/**
	 * Indication of how important/urgent this task is
	 */
	int priority;

	/**
	 * The {@link User.getId() userId} of the person that is responsible for
	 * this task.
	 */
	String owner;

	/**
	 * The {@link User.getId() userId} of the person to which this task is
	 * delegated.
	 */
	String assignee;

	/**
	 * Reference to the process instance or null if it is not related to a
	 * process instance.
	 */
	String processInstanceId;

	/**
	 * Reference to the path of execution or null if it is not related to a
	 * process instance.
	 */
	String executionId;

	/**
	 * Reference to the process definition or null if it is not related to a
	 * process.
	 */
	String processDefinitionId;

	/** The date/time when this task was created */
	String createTime;

	/**
	 * The id of the activity in the process defining this task or null if this is
	 * not related to a process
	 */
	String taskDefinitionKey;

	/**
	 * Due date of the task.
	 */
	String dueDate;

	/**
	 * The category of the task. This is an optional field and allows to 'tag'
	 * tasks as belonging to a certain category.
	 */
	String category;

	/**
	 * The parent task for which this task is a subtask
	 */
	String parentTaskId;

	/**
	 * The tenant identifier of this task
	 */
	String tenantId;

	/**
	 * The form key for the user task
	 */
	String formKey;

	/**
	 * Returns the local task variables if requested in the task query
	 */
	Map<String, Object> taskLocalVariables;

	/**
	 * Returns the process variables if requested in the task query
	 */
	Map<String, Object> processVariables;

	public String getAssignee() {
		return assignee;
	}

	public String getCategory() {
		return category;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getDescription() {
		return description;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getExecutionId() {
		return executionId;
	}

	public String getFormKey() {
		return formKey;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public int getPriority() {
		return priority;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public Map<String, Object> getTaskLocalVariables() {
		return taskLocalVariables;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
		this.taskLocalVariables = taskLocalVariables;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", priority=" + priority
				+ ", owner=" + owner + ", assignee=" + assignee + ", processInstanceId=" + processInstanceId
				+ ", executionId=" + executionId + ", processDefinitionId=" + processDefinitionId + ", createTime="
				+ createTime + ", taskDefinitionKey=" + taskDefinitionKey + ", dueDate=" + dueDate + ", category="
				+ category + ", parentTaskId=" + parentTaskId + ", tenantId=" + tenantId + ", formKey=" + formKey
				+ ", taskLocalVariables=" + taskLocalVariables + ", processVariables=" + processVariables + "]";
	}

}
