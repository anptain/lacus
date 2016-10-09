package com.winterfell.lacus.entity;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {
	private static final long serialVersionUID = -7179844817539115010L;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
