package com.csis3275.banking.model;

import com.csis3275.login.model.User_group3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Banking")
public class Banking_group3 {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //account number
	@Column(name = "user_id")
	private Long userId;
	private String name;
	private float balance;
	private int transferLimit;
	private String type;
	
	
	
//	@ManyToOne
//    @JoinColumn(name = "user_id") // Define the foreign key column
//	private User_group3 user;
	
	
	public Banking_group3() {
		super();
	}

	public Banking_group3(String name, float balance, int transferLimit, String type) {
		super();
		this.name = name;
		this.balance = balance;
		this.transferLimit = transferLimit;
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

	public int getTransferLimit() {
		return transferLimit;
	}

	public void setTransferLimit(int transferLimit) {
		this.transferLimit = transferLimit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	

}
