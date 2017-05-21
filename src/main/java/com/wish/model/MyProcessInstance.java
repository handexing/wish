package com.wish.model;

import java.util.Map;

/**
 * @ClassName: ProcessInstance
 * @Description:流程图运行实例
 * @author handx 908716835@qq.com
 * @date 2017年5月21日 下午5:18:17
 *
 */

public class MyProcessInstance {

	/**
	 * The id of the process definition of the process instance.
	 */
	private String processDefinitionId;

	/**
	 * The business key of this process instance.
	 */
	private String businessKey;

	/**
	 * The deployment id of the process definition of the process instance.
	 */
	private String deploymentId;

	/**
	 * Returns the description of this process instance.
	 */
	private String description;

	/**
	 * Returns the localized description of this process instance.
	 */
	private String localizedDescription;

	/**
	 * Returns the localized name of this process instance.
	 */
	private String localizedName;

	/**
	 * Returns the name of this process instance.
	 */
	private String name;

	/**
	 * The key of the process definition of the process instance.
	 */
	private String processDefinitionKey;

	/**
	 * The name of the process definition of the process instance.
	 */
	private String processDefinitionName;

	/**
	 * The version of the process definition of the process instance.
	 */
	private Integer processDefinitionVersion;

	/**
	 * Returns the process variables if requested in the process instance query
	 */
	private Map<String, Object> processVariables;

	/**
	   * returns true if the process instance is suspended
	   */
	private boolean suspended;

	/**
	 * The tenant identifier of this process instance
	 */
	private String tenantId;

	public String getBusinessKey() {
		return businessKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public String getDescription() {
		return description;
	}

	public String getLocalizedDescription() {
		return localizedDescription;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public String getName() {
		return name;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public Integer getProcessDefinitionVersion() {
		return processDefinitionVersion;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public String getTenantId() {
		return tenantId;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocalizedDescription(String localizedDescription) {
		this.localizedDescription = localizedDescription;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
		this.processDefinitionVersion = processDefinitionVersion;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "ProcessInstance [processDefinitionId=" + processDefinitionId + ", businessKey=" + businessKey
				+ ", deploymentId=" + deploymentId + ", description=" + description + ", localizedDescription="
				+ localizedDescription + ", localizedName=" + localizedName + ", name=" + name
				+ ", processDefinitionKey=" + processDefinitionKey + ", processDefinitionName=" + processDefinitionName
				+ ", processDefinitionVersion=" + processDefinitionVersion + ", processVariables=" + processVariables
				+ ", suspended=" + suspended + ", tenantId=" + tenantId + "]";
	}

}
