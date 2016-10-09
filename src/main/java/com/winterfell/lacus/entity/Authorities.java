package com.winterfell.lacus.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authorities extends AbstractEntity implements GrantedAuthority {
	private static final long serialVersionUID = -1359966519950333019L;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String role;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.getRole();
	}
}
