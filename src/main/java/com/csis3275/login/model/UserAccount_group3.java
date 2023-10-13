package com.csis3275.login.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount_group3 {

	@Id
	private Long accountNumber;
	
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	
	public UserAccount_group3() {
		super();
	}

	public UserAccount_group3(String email, String password, String firstName, String lastName, Long accNumber) {
		
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.accountNumber = accNumber;
		this.lastName = lastName;
	}
	
	
	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}