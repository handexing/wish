package com.wish.model;

public class ImgaeVo {
	private String imgurl;

	private Long projectid;

	private String createtime;

	private String flagId;

	private String title;

	public ImgaeVo() {
		super();
	}

	public String getCreatetime() {
		return createtime;
	}

	public String getFlagId() {
		return flagId;
	}

	public String getImgurl() {
		return imgurl;
	}

	public Long getProjectid() {
		return projectid;
	}

	public String getTitle() {
		return title;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public void setFlagId(String flagId) {
		this.flagId = flagId;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
