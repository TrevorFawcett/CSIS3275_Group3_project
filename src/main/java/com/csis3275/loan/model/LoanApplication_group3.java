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
@Table(name="Applications")
public class LoanApplication_group3 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String type;
	private float amount;
	private float rate;
	private int loanTerm;
	private float totalToBePaid;
	private boolean accepted;
	
	@ManyToOne
    @JoinColumn(name = "user_id") // Define the foreign key column
	private User_group3 user;
	
	public LoanApplication_group3(String type, float amount, float rate, int loanTerm, float totalToBePaid,
			User_group3 user) {
		super();
		this.type = type;
		this.amount = amount;
		this.rate = rate;
		this.loanTerm = loanTerm;
		this.totalToBePaid = totalToBePaid;
		this.user = user;
	}
}
