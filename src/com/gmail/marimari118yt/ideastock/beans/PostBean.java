package com.gmail.marimari118yt.ideastock.beans;

import java.io.Serializable;

public class PostBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String title = null;
	private String content = null;
	private String createdAt = null;

	public PostBean() {
		// empty
	}

	public PostBean(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public PostBean(String title, String content, String createdAt) {
		this(title, content);
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
