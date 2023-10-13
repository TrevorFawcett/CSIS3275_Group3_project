package com.csis3275.loan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Loans")
public class Loan_group3 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String loan_type;
	private float amount;
	private float rate;
	private int loanTerm;
	private double totalToBePaid;
	
	
	
	
	public Loan_group3() {
		super();
	}

	public Loan_group3(String loan_type, float amount, float rate, int loanTerm, double totalToBePaid) {
		super();
		this.loan_type = loan_type;
		this.amount = amount;
		this.rate = rate;
		this.loanTerm = loanTerm;
		this.totalToBePaid = totalToBePaid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoan_type() {
		return loan_type;
	}

	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
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

	public double getTotalToBePaid() {
		return totalToBePaid;
	}

	public void setTotalToBePaid(double totalToBePaid) {
		this.totalToBePaid = totalToBePaid;
	}

}
