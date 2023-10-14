package com.csis3275.Credit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	public Credit_group3() {
		super();
	}

	public Credit_group3(String name, float balance, int creditLimit, float interest, String type) {
		super();
		this.name = name;
		this.balance = balance;
		this.creditLimit = creditLimit;
		this.interest = interest;
		this.type = type;
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