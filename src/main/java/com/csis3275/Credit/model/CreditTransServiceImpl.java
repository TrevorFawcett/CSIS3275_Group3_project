package com.csis3275.Credit.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.Credit.Repositories.ICreditTransRepository;
import com.csis3275.banking.model.BankingTrans_group3;

@Service
public class CreditTransServiceImpl {

	
	@Autowired
	ICreditTransRepository creditTransService;
	
	

	//CREATE
	public CreditTrans_group3 createCreditTransaction(CreditTrans_group3 newCreditTransaction) {
		return creditTransService.save(newCreditTransaction);
	}
	
	
	//READ BY ONE
	public CreditTrans_group3 readSingleCreditTransaction(Long id) {
		return creditTransService.findById(id).get();
	}

	
	//READ
	
	public List<CreditTrans_group3> readCreditTransactions() {
		return (List<CreditTrans_group3>) creditTransService.findAll();
	}
	
	
	//READ BY ID
	
	public List<CreditTrans_group3> readCreditTransactionsById(Long Id) {
		return creditTransService.findAllById(Id);
	}
	
	
	
}
