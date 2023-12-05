package com.csis3275.login.model;

public class PasswordResetForm_group3 {
	
	String password1;
	String password2;
	String userName;
	
	
	
	public boolean comparePassword(String password1, String password2) {
		
		if(password1.equals(password2)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
