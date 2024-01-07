package com.gmail.marimari118yt.ideastock.dto;

import com.gmail.marimari118yt.ideastock.beans.PostBean;
import com.gmail.marimari118yt.ideastock.beans.QuestionBean;
import com.gmail.marimari118yt.ideastock.beans.UserBean;

public class QuestionPostDTO {
	
	private static final int TITLE_MAX_LENGTH = 256;
	private static final int CONTENT_MAX_LENGTH = 1024;

	private int authorId = -1;
	private String title = null;
	private String content = null;
	
	public QuestionPostDTO() {
		// empty
	}

	public QuestionPostDTO authorId(int authorId) {
		this.authorId = authorId;
		return this;
	}

	public QuestionPostDTO title(String title) {
		this.title = title;
		return this;
	}

	public QuestionPostDTO content(String content) {
		this.content = content;
		return this;
	}
	
	public QuestionBean build() throws ValidationException {
		ValidationException err = new ValidationException();
		
		if (authorId <= 0) {
			err.addDetail("authorId", "ユーザーIDが不正な値です。");
		}
		
		if (title == null) {
			err.addDetail("title", "質問のタイトルが不正な値です。");
			
		} else {
			if (title.length() > TITLE_MAX_LENGTH) {
				err.addDetail("title", "質問のタイトルが長すぎます。");
				
			} else if (title.length() <= 0) {
				err.addDetail("title", "質問のタイトルが空欄です。");
			}
		}
		
		if (content == null) {
			err.addDetail("content", "質問の内容が不正な値です。");
			
		} else {
			if (content.length() > CONTENT_MAX_LENGTH) {
				err.addDetail("content", "質問の内容が長すぎます。");
				
			} else if (content.length() <= 0) {
				err.addDetail("content", "質問の内容が空欄です。");
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
		
		return new QuestionBean(author, post);
	}

}
