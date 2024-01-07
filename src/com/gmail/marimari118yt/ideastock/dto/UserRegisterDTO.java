package com.gmail.marimari118yt.ideastock.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.gmail.marimari118yt.ideastock.beans.UserBean;

public class UserRegisterDTO {

	private static final int NAME_MAX_LENGTH = 64;
	private static final int LOGIN_ID_MAX_LENGTH = 64;
	private static final int LOGIN_ID_MIN_LENGTH = 4;
	private static final int PASSWORD_MAX_LENGTH = 64;
	private static final int PASSWORD_MIN_LENGTH = 8;
	
	private String name = null;
	private String loginId = null;
	private String password = null;
	
	public UserRegisterDTO() {
		// empty
	}

	public UserRegisterDTO name(String name) {
		this.name = name;
		return this;
	}

	public UserRegisterDTO loginId(String loginId) {
		this.loginId = loginId;
		return this;
	}

	public UserRegisterDTO password(String password) {
		this.password = password;
		return this;
	}
	
	public UserBean build() throws ValidationException, NoSuchAlgorithmException {
		ValidationException err = new ValidationException();
		
		if (name == null) {
			err.addDetail("name", "表示名が不正な値です。");
			
		} else {
			if (name.length() > NAME_MAX_LENGTH) {
				err.addDetail("name", "表示名が長すぎます。");
				
			} else if (name.length() <= 0) {
				err.addDetail("name", "表示名が空欄です。");
			}
		}
		
		if (loginId == null) {
			err.addDetail("loginId", "ログインIDが不正な値です。");
			
		} else {
			if (loginId.length() > LOGIN_ID_MAX_LENGTH) {
				err.addDetail("loginId", "ログインIDが長すぎます。");
				
			} else if (loginId.length() <= 0) {
				err.addDetail("loginId", "ログインIDが空欄です。");
				
			} else if (loginId.length() < LOGIN_ID_MIN_LENGTH) {
				err.addDetail("loginId", "ログインIDが短すぎます。");
			}
		}
		
		if (password == null) {
			err.addDetail("password", "パスワードが不正な値です。");
			
		} else {
			if (password.length() > PASSWORD_MAX_LENGTH) {
				err.addDetail("password", "パスワードが長すぎます。");
				
			} else if (password.length() < PASSWORD_MIN_LENGTH) {
				err.addDetail("password", "パスワードが短すぎます。");
				
			} else if (password.length() <= 0) {
				err.addDetail("password", "パスワードが空欄です。");
			}
		}
		
		if (!err.getDetails().isEmpty()) {
			throw err;
		}
		
		UserBean user = new UserBean();
		user.setName(name);
		user.setLoginId(loginId);
		user.setPassword(MessageDigest.getInstance("SHA-256")
				.digest(password.getBytes()));
		
		return user;
	}

}
