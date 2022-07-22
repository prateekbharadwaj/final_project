package com.user.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginDTO {
	@NotNull
	private int userId;
	
	@Size(min = 7, max = 15, message = "Password should have at least 7 or less than 15 characters and minimun one special character")
	//@Pattern(regexp="?=.*?[#?!@$%^&*-]", message="Password should have at least 7 or less than 15 characters and minimun one special character")
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
