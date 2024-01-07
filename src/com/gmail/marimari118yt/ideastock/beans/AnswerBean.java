package com.gmail.marimari118yt.ideastock.beans;

import java.io.Serializable;

public class AnswerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id = -1;
	private int questionId = -1;
	private UserBean author = null;
	private PostBean post = null;

	public AnswerBean() {
		// empty
	}

	public AnswerBean(int questionId, UserBean author, PostBean post) {
		this.questionId = questionId;
		this.author = author;
		this.post = post;
	}

	public AnswerBean(int id, int questionId, UserBean author, PostBean post) {
		this(questionId, author, post);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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
