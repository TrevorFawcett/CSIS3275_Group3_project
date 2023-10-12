package com.csis3275.login.model;

public class UserDto_group3 {
	
	private String email;
	private String password;
	private String role;
	private Long accNumber;
	
	public UserDto_group3(String email, String password, String role, Long accNumber) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.accNumber = accNumber;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(Long accNumber) {
		this.accNumber = accNumber;
	}

	
	
	
}
