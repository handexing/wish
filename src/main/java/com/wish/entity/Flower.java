package com.wish.entity;

public class Flower {

	private Long id;
	private String name;
	private String url;
	private String imgPath;
	private String nick;

	public Long getId() {
		return id;
	}

	public String getImgPath() {
		return imgPath;
	}

	public String getName() {
		return name;
	}

	public String getNick() {
		return nick;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Flower [ name=" + name + ", url=" + url + ", imgPath=" + imgPath + ", nick=" + nick + "]";
	}

}
