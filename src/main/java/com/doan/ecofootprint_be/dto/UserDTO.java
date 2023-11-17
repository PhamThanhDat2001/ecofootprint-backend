package com.doan.ecofootprint_be.dto;


import com.doan.ecofootprint_be.entity.Users;

public class UserDTO {

	private String userName;

	private String email;

	private String password;

	public UserDTO() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users toEntity() {
		return new Users(userName, email, password);
	}
}