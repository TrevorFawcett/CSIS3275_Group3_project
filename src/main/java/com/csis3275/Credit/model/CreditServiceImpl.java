package com.csis3275.Credit.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.Credit.Repositories.ICreditRepository;

@Service
public class CreditServiceImpl {

	
	@Autowired
	private ICreditRepository creditRepository;

	// CREATE
	public Credit_group3 createCreditCardAccounts(Credit_group3 newCreditCard) {
		return creditRepository.save(newCreditCard);
	}

	// READ
	public List<Credit_group3> readCreditCardAccounts() {
		return (List<Credit_group3>) creditRepository.findAll();
	}

	// READ BY ID
	public List<Credit_group3> readCreditCardAccountsById(Long Id) {
		return (List<Credit_group3>) creditRepository.findAllById(Id);
	}
	
	//READ BY ONE
	public Credit_group3 readSingleCreditAccount(Long id) {
		return creditRepository.findById(id).get();
	}
	
	//DELETE
		public void DeleteAccount(Long id) {
			creditRepository.deleteById(id);
		}

	// Get all account balances by Id
	public List<Float> getAllAccountBalancesById(Long Id) {
		List<Credit_group3> accounts = (List<Credit_group3>) creditRepository.findAllById(Id);
		List<Float> accountBalances = accounts.stream().map(Credit_group3::getBalance) // Extract the balance of each
																						// account
				.toList();
		return accountBalances;
	}

	// Get all account balances
	public List<Float> getAllAccountBalances() {
		List<Credit_group3> accounts = (List<Credit_group3>) creditRepository.findAll();
		List<Float> accountBalances = accounts.stream().map(Credit_group3::getBalance) // Extract the balance of each
																						// account
				.toList();
		return accountBalances;
	}

}
