package com.csis3275.loan.model;

import java.util.List;

import com.csis3275.Credit.model.CreditTrans_group3;
import com.csis3275.login.model.User_group3;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Loan")
public class Loan_group3 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private float amount; // initial starting amount
	private float rate;
	private int loanTerm;
	private float totalToBePaid; //starting amount + interest, also current balance
	
	
	
	@ManyToOne
    @JoinColumn(name = "user_id") // Define the foreign key column
	private User_group3 user;
	
	@OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanTrans_group3> loanTransactions;
	
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
	
	public float CreatePayment() {	
		float payment;
		//float amount = this.getAmount();
		
		
		if (this.type.equals("Car")) {
			this.setRate(0.03f); } 
		else if (this.type.equals("Home")) { 
			this.setRate(0.075f);}
		
		payment = this.amount * this.loanTerm * this.rate;
		//payment = amount * rate * loanTerm; 
		return payment;
	}
	
	public void updateBalance(float transAmount) {
		float newBalance = this.totalToBePaid - transAmount;
		this.setTotalToBePaid(newBalance);
		
		
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
