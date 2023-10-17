package com.csis3275.Credit.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.Credit.Repositories.ICreditRepository;
import com.csis3275.Credit.model.Credit_group3;
import com.csis3275.banking.model.Banking_group3;

@Service
public class CreditServiceImpl {

	@Autowired
	private ICreditRepository creditService;

	// CREATE
	public Credit_group3 createCreditCardAccounts(Credit_group3 newCreditCard) {
		return creditService.save(newCreditCard);
	}

	// READ
	public List<Credit_group3> readCreditCardAccounts() {
		return (List<Credit_group3>) creditService.findAll();
	}

	// READ BY ID
	public List<Credit_group3> readCreditCardAccountsById(Long Id) {
		return (List<Credit_group3>) creditService.findAllById(Id);
	}
	
	//READ BY ONE
	public Credit_group3 readSingleCreditAccount(Long id) {
		return creditService.findById(id).get();
	}
	
	//DELETE
		public void DeleteAccount(Long id) {
			creditService.deleteById(id);
		}

	// Get all account balances by Id
	public List<Float> getAllAccountBalancesById(Long Id) {
		List<Credit_group3> accounts = (List<Credit_group3>) creditService.findAllById(Id);
		List<Float> accountBalances = accounts.stream().map(Credit_group3::getBalance) // Extract the balance of each
																						// account
				.toList();
		return accountBalances;
	}

	// Get all account balances
	public List<Float> getAllAccountBalances() {
		List<Credit_group3> accounts = (List<Credit_group3>) creditService.findAll();
		List<Float> accountBalances = accounts.stream().map(Credit_group3::getBalance) // Extract the balance of each
																						// account
				.toList();
		return accountBalances;
	}

}
