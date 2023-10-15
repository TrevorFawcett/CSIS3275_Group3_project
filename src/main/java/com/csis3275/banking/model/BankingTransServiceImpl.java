package com.csis3275.banking.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.banking.repositories.IBankingTransRepository;



@Service
public class BankingTransServiceImpl {
	
	
	
	@Autowired
	IBankingTransRepository bankingTransService;
	
	
	//CREATE
	public BankingTrans_group3 createBankingTransaction(BankingTrans_group3 newBankingTransaction) {
		return bankingTransService.save(newBankingTransaction);
	}
	
	
	//READ BY ONE
	public BankingTrans_group3 readSingleBankingTransaction(Long id) {
		return bankingTransService.findById(id).get();
	}

	
	//READ
	
	public List<BankingTrans_group3> readBankingTransactions() {
		return (List<BankingTrans_group3>) bankingTransService.findAll();
	}
	
	
	//READ BY ID
	
	public List<BankingTrans_group3> readBankingTransactionsById(Long Id) {
		return bankingTransService.findAllById(Id);
	}
	
	
	
}
