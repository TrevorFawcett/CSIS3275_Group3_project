package com.csis3275.loan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.Credit.Repositories.ICreditTransRepository;
import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.banking.model.Banking_group3;
import com.csis3275.loan.repositories.ILoanTransRepository;

@Service
public class LoanTransServiceImpl {

	
	@Autowired
	ILoanTransRepository loanTransService;
	//ICreditTransRepository creditTransService;
	
	

	//CREATE
	public LoanTrans_group3 createLoanTransaction(LoanTrans_group3 newLoanTransaction) {
		return loanTransService.save(newLoanTransaction);
	}
	
	
	
	
	//READ BY ONE
	public LoanTrans_group3 readSingleLoanTransaction(Long id) {
		return loanTransService.findById(id).get();
	}

	
	//READ
	
	public List<LoanTrans_group3> readLoanTransactions() {
		return (List<LoanTrans_group3>) loanTransService.findAll();
	}
	
	
	//READ BY ID
	
	public List<LoanTrans_group3> readLoanTransactionsById(Long Id) {
		return loanTransService.findAllById(Id);
	}
	
	
	
}
