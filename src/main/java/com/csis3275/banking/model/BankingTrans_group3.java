package com.csis3275.banking.model;

import java.util.Optional;

import com.csis3275.login.model.User_group3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="bankingTrans")
public class BankingTrans_group3 {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String type;
	private float amount;
	private String description;
	private float withdrawal;
	private float deposit;
	
	
	@ManyToOne
    @JoinColumn(name = "account_id") // Define the foreign key column
	private Banking_group3 banking;


	public BankingTrans_group3() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BankingTrans_group3(String type, float amount, String description, float withdrawal, float deposit,
			Banking_group3 banking) {
		super();
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.withdrawal = withdrawal;
		this.deposit = deposit;
		this.banking = banking;
	}

	
	

	public float getWithdrawal() {
		return withdrawal;
	}


	public void setWithdrawal(float withdrawal) {
		this.withdrawal = withdrawal;
	}



	public float getDeposit() {
		return deposit;
	}



	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}



	public long getId() {
		return Id;
	}


	public void setId(long id) {
		Id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Banking_group3 getBanking() {
		return banking;
	}


	public void setBanking(Banking_group3 banking) {
		this.banking = banking;
	}


	
	
	
	
	
	

}
