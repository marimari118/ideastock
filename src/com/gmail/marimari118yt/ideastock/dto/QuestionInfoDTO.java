package com.gmail.marimari118yt.ideastock.dto;

import com.gmail.marimari118yt.ideastock.beans.PostBean;
import com.gmail.marimari118yt.ideastock.beans.QuestionBean;
import com.gmail.marimari118yt.ideastock.beans.UserBean;

public class QuestionInfoDTO {
	
	private int id = -1;
	private int authorId = -1;
	private String authorName = null;
	private String title = null;
	private String content = null;
	private String createdAt = null;
	
	public QuestionInfoDTO() {
		// empty
	}

	public QuestionInfoDTO id(int id) {
		this.id = id;
		return this;
	}

	public QuestionInfoDTO authorId(int authorId) {
		this.authorId = authorId;
		return this;
	}

	public QuestionInfoDTO authorName(String authorName) {
		this.authorName = authorName;
		return this;
	}

	public QuestionInfoDTO title(String title) {
		this.title = title;
		return this;
	}

	public QuestionInfoDTO content(String content) {
		this.content = content;
		return this;
	}

	public QuestionInfoDTO createdAt(String createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public QuestionBean build() {
		UserBean author = new UserBean();
		PostBean post = new PostBean();
		
		author.setId(authorId);
		author.setName(authorName);
		post.setTitle(title);
		post.setContent(content);
		post.setCreatedAt(createdAt);
		
		return new QuestionBean(id, author, post);
	}

}
