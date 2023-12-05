package com.csis3275.Credit.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.Credit.Repositories.ICreditTransRepository;
import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.banking.model.Banking_group3;

@Service
public class CreditTransServiceImpl {

	
	@Autowired
	ICreditTransRepository creditTransService;
	
	

	//CREATE
	public CreditTrans_group3 createCreditTransaction(CreditTrans_group3 newCreditTransaction) {
		return creditTransService.save(newCreditTransaction);
	}
	
	//save transfer transaction
		public CreditTrans_group3 createCreditTransactionTransfer(String senderOrReciever, float amount, Credit_group3 credit) {
			
			CreditTrans_group3 trans = new CreditTrans_group3();
			if (senderOrReciever == "sender") {
				
				trans.setType("withdrawal");
				credit.setBalance((float) (credit.getBalance() + amount));
				trans.setDescription("MONEY TRANSFER");

			}else {										
				trans.setType("deposit");
				credit.setBalance((float) (credit.getBalance() - amount));
				trans.setDescription("PAYMENT");
				
			}
			trans.setAmount(amount);
			trans.setCredit(credit);
			trans.setRefunded(false);
			
			return creditTransService.save(trans);
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
