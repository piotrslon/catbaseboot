package com.catbaseboot.dto;

import javax.validation.constraints.Size;

public class RegisterFormDto {
	
	
	@Size(min = 4, max = 16)
	private String username;
	
	@Size(min = 4, max = 20)
	private String password;
	
	private String email;
	
	/*getters and setters*/
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}