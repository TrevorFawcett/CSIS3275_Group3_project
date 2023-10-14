package com.csis3275.Credit.model;

import com.csis3275.login.model.User_group3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name="credit")
public class Credit_group3 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //account number
	private String name;
	private float balance;
	private int creditLimit;
	private float interest;
	private String type;
	
	
	@ManyToOne
    @JoinColumn(name = "user_id") // Define the foreign key column
	private User_group3 user;

	public Credit_group3() {
		super();
	}

	public Credit_group3(String name, float balance, int creditLimit, float interest, String type, User_group3 user) {
		super();
		this.name = name;
		this.balance = balance;
		this.creditLimit = creditLimit;
		this.interest = interest;
		this.type = type;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getLimit() {
		return creditLimit;
	}

	public void setLimit(int limit) {
		this.creditLimit = limit;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
