package com.csis3275.banking.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.banking.repositories.IBankingTransRepository;
import com.csis3275.banking.model.Banking_group3;



@Service
public class BankingTransServiceImpl {
	
	
	
	@Autowired
	IBankingTransRepository bankingTransService;
	
	
	//CREATE
	public BankingTrans_group3 createBankingTransaction(BankingTrans_group3 newBankingTransaction) {
		return bankingTransService.save(newBankingTransaction);
	}
	
	
	//save transfer transaction
	public BankingTrans_group3 createBankingTransactionTransfer(String senderOrReciever, float amount, Banking_group3 banking) {
		
		BankingTrans_group3 trans = new BankingTrans_group3();
		
		if (senderOrReciever == "sender") {
			
			trans.setType("withdrawal");
			trans.setWithdrawal(amount);
			trans.setDeposit(0.0f);
			banking.setBalance((float) (banking.getBalance() - amount));

		}else {										
			trans.setType("deposit");
			trans.setWithdrawal(0.0f);
			trans.setDeposit(amount);
			banking.setBalance((float) (banking.getBalance() + amount));
			
		}
		
		trans.setAmount(amount);
		trans.setDescription("MONEY TRANSFER");
		trans.setBanking(banking);
		trans.setRefunded(false);
		
		return bankingTransService.save(trans);
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
