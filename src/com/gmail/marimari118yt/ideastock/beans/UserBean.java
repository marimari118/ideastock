package com.gmail.marimari118yt.ideastock.beans;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id = -1;
	private String name = null;
	private String loginId = null;
	private byte[] password = null;
	private String createdAt = null;

	public UserBean() {
		// empty
	}

	public UserBean(String name, String loginId, byte[] password) {
		this.name = name;
		this.loginId = loginId;
		this.password = password;
	}

	public UserBean(int id, String name, String loginId, byte[] password, String createdAt) {
		this(name, loginId, password);
		this.id = id;
		this.createdAt = createdAt;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
