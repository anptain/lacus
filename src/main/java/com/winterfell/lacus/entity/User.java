package com.winterfell.lacus.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.winterfell.lacus.common.UserStatus;

@Entity
public class User extends AbstractEntity {
	private static final long serialVersionUID = -7179844817539115010L;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	private String phone;

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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
