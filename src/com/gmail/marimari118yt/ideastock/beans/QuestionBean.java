package com.gmail.marimari118yt.ideastock.beans;

import java.io.Serializable;

public class QuestionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id = -1;
	private UserBean author = null;
	private PostBean post = null;

	public QuestionBean() {
		// empty
	}

	public QuestionBean(UserBean author, PostBean post) {
		this.author = author;
		this.post = post;
	}

	public QuestionBean(int id, UserBean author, PostBean post) {
		this(author, post);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserBean getAuthor() {
		return author;
	}

	public void setAuthor(UserBean author) {
		this.author = author;
	}

	public PostBean getPost() {
		return post;
	}

	public void setPost(PostBean post) {
		this.post = post;
	}

}
