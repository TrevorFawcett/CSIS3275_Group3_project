package com.csis3275.loan.model;

import com.csis3275.login.model.User_group3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Loans")
public class Loan_group3 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String type;
	private float amount;
	private float rate;
	private int loanTerm;
	private float totalToBePaid;
	
	
	
	@ManyToOne
    @JoinColumn(name = "user_id") // Define the foreign key column
	private User_group3 user;
	
	public Loan_group3() {
		super();
	}

	public Loan_group3(String type, float amount, float rate, int loanTerm, float totalToBePaid,
			User_group3 user) {
		super();
		this.type = type;
		this.amount = amount;
		this.rate = rate;
		this.loanTerm = loanTerm;
		this.totalToBePaid = totalToBePaid;
		this.user = user;
	}
	
	public float CreatePayment(float amount, float rate, int loanTerm, String type) {	
		float payment;
		
		payment = amount * rate * loanTerm; 
		return payment;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User_group3 getUser() {
		return user;
	}

	public void setUser(User_group3 user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public float getTotalToBePaid() {
		return totalToBePaid;
	}

	public void setTotalToBePaid(float totalToBePaid) {
		this.totalToBePaid = totalToBePaid;
	}

}
