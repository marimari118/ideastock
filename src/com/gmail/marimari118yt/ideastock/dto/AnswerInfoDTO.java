package com.gmail.marimari118yt.ideastock.dto;

import com.gmail.marimari118yt.ideastock.beans.AnswerBean;
import com.gmail.marimari118yt.ideastock.beans.PostBean;
import com.gmail.marimari118yt.ideastock.beans.UserBean;

public class AnswerInfoDTO {
	
	private int id = -1;
	private int questionId = -1;
	private int authorId = -1;
	private String authorName = null;
	private String title = null;
	private String content = null;
	private String createdAt = null;
	
	public AnswerInfoDTO() {
		// empty
	}

	public AnswerInfoDTO id(int id) {
		this.id = id;
		return this;
	}

	public AnswerInfoDTO questionId(int questionId) {
		this.questionId = questionId;
		return this;
	}

	public AnswerInfoDTO authorId(int authorId) {
		this.authorId = authorId;
		return this;
	}

	public AnswerInfoDTO authorName(String authorName) {
		this.authorName = authorName;
		return this;
	}

	public AnswerInfoDTO title(String title) {
		this.title = title;
		return this;
	}

	public AnswerInfoDTO content(String content) {
		this.content = content;
		return this;
	}

	public AnswerInfoDTO createdAt(String createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public AnswerBean build() {
		UserBean author = new UserBean();
		PostBean post = new PostBean();
		
		author.setId(authorId);
		author.setName(authorName);
		post.setTitle(title);
		post.setContent(content);
		post.setCreatedAt(createdAt);
		
		return new AnswerBean(id, questionId, author, post);
	}

}
