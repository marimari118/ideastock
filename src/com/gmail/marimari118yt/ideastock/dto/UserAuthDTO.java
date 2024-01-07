package com.gmail.marimari118yt.ideastock.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.gmail.marimari118yt.ideastock.beans.UserBean;

public class UserAuthDTO {
	
	private String loginId = null;
	private String password = null;
	
	public UserAuthDTO() {
		// empty
	}

	public UserAuthDTO loginId(String loginId) {
		this.loginId = loginId;
		return this;
	}

	public UserAuthDTO password(String password) {
		this.password = password;
		return this;
	}
	
	public UserBean build() throws ValidationException, NoSuchAlgorithmException {
		ValidationException err = new ValidationException();
		
		if (loginId == null) {
			err.addDetail("loginId", "ログインIDが不正な値です。");
			
		} else if (loginId.length() <= 0) {
			err.addDetail("loginId", "ログインIDが空欄です。");
		}
		
		if (password == null) {
			err.addDetail("password", "パスワードが不正な値です。");
			
		} else if (password.length() <= 0) {
			err.addDetail("password", "パスワードが空欄です。");
		}
		
		if (!err.getDetails().isEmpty()) {
			throw err;
		}
		
		UserBean user = new UserBean();
		user.setLoginId(loginId);
		user.setPassword(MessageDigest.getInstance("SHA-256")
				.digest(password.getBytes()));
		
		return user;
	}

}
