package com.winterfell.lacus.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class SmsCaptcha extends AbstractEntity {
	private static final long serialVersionUID = -5420381888830670594L;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String code;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
