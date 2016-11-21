package com.catbaseboot.model;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
	
	/**
	 * poczoto2
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	public CurrentUser(User user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("USER", "ADMIN"));
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}
