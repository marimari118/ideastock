package com.gmail.marimari118yt.ideastock.dto;

import com.gmail.marimari118yt.ideastock.beans.AnswerBean;
import com.gmail.marimari118yt.ideastock.beans.PostBean;
import com.gmail.marimari118yt.ideastock.beans.UserBean;

public class AnswerPostDTO {
	
	private static final int TITLE_MAX_LENGTH = 256;
	private static final int CONTENT_MAX_LENGTH = 1024;

	private int questionId = -1;
	private int authorId = -1;
	private String title = null;
	private String content = null;
	
	public AnswerPostDTO() {
		// empty
	}

	public AnswerPostDTO questionId(int questionId) {
		this.questionId = questionId;
		return this;
	}

	public AnswerPostDTO authorId(int authorId) {
		this.authorId = authorId;
		return this;
	}

	public AnswerPostDTO title(String title) {
		this.title = title;
		return this;
	}

	public AnswerPostDTO content(String content) {
		this.content = content;
		return this;
	}
	
	public AnswerBean build() throws ValidationException {
		ValidationException err = new ValidationException();
		
		if (authorId <= 0) {
			err.addDetail("userId", "ユーザーIDが不正な値です。");
		}
		
		if (questionId <= 0) {
			err.addDetail("questionId", "質問IDが不正な値です。");
		}
		
		if (title == null) {
			err.addDetail("title", "回答のタイトルが不正な値です。");
			
		} else {
			if (title.length() > TITLE_MAX_LENGTH) {
				err.addDetail("title", "回答のタイトルが長すぎます。");
				
			} else if (title.length() <= 0) {
				err.addDetail("title", "回答のタイトルが空欄です。");
			}
		}
		
		if (content == null) {
			err.addDetail("content", "回答の内容が不正な値です。");
			
		} else {
			if (content.length() > CONTENT_MAX_LENGTH) {
				err.addDetail("content", "回答の内容が長すぎます。");
				
			} else if (content.length() <= 0) {
				err.addDetail("content", "回答の内容が空欄です。");
			}
		}
		
		if (!err.getDetails().isEmpty()) {
			throw err;
		}
		
		UserBean author = new UserBean();
		PostBean post = new PostBean();
		
		author.setId(authorId);
		post.setTitle(title);
		post.setContent(content);
		
		return new AnswerBean(questionId, author, post);
	}

}
