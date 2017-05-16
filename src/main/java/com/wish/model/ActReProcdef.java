package com.wish.model;

public class ActReProcdef {

	private String id;
	private Integer rev;
	private String category;
	private String name;
	private String key;
	private Integer version;
	private String deploymentId;
	private String resourceName;
	private String diagramResourceName;
	private String description;
	private Long hasStartFormKey;
	private Long hasGraphicalNotation;
	private Integer suspensionState;
	private String tenantId;

	public String getCategory() {
		return category;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public String getDescription() {
		return description;
	}

	public String getDiagramResourceName() {
		return diagramResourceName;
	}

	public Long getHasGraphicalNotation() {
		return hasGraphicalNotation;
	}

	public Long getHasStartFormKey() {
		return hasStartFormKey;
	}

	public String getId() {
		return id;
	}
	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getResourceName() {
		return resourceName;
	}

	public Integer getRev() {
		return rev;
	}
	public Integer getSuspensionState() {
		return suspensionState;
	}

	public String getTenantId() {
		return tenantId;
	}

	public Integer getVersion() {
		return version;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}


	public void setHasGraphicalNotation(Long hasGraphicalNotation) {
		this.hasGraphicalNotation = hasGraphicalNotation;
	}

	public void setHasStartFormKey(Long hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}
	public void setSuspensionState(Integer suspensionState) {
		this.suspensionState = suspensionState;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}


}
