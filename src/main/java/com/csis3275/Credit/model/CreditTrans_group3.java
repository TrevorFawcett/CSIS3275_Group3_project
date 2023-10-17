package com.csis3275.Credit.model;

import java.util.List;

import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.banking.model.Banking_group3;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="creditTrans")
public class CreditTrans_group3 {
	
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String type;
	private String description;
	private float amount;
	private boolean isRefunded;
	
	@ManyToOne
    @JoinColumn(name = "account_id",nullable = true) // Define the foreign key column
	private Credit_group3 credit;
	
	

	public CreditTrans_group3() {
		super();
		
	}

	public CreditTrans_group3(String type, String description, float amount, Credit_group3 credit, boolean isRefunded) {
		super();
		this.type = type;
		this.description = description;
		this.amount = amount;
		this.credit = credit;
		this.isRefunded = isRefunded;
	}





	public boolean isRefunded() {
		return isRefunded;
	}

	public void setRefunded(boolean isRefunded) {
		this.isRefunded = isRefunded;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Credit_group3 getCredit() {
		return credit;
	}


	public void setCredit(Credit_group3 credit) {
		this.credit = credit;
	}
	
	
	
	
	

}
