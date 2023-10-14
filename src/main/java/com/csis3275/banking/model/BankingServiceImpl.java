package com.csis3275.banking.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.banking.repositories.IBankingRepository;
import com.csis3275.banking.model.Banking_group3;




@Service
public class BankingServiceImpl {
	
	@Autowired
	private IBankingRepository bankingRepository;
	
	
	//CREATE
	public Banking_group3 createBankingAccounts(Banking_group3 newBanking)	{
		return bankingRepository.save(newBanking);
	}
	
	//READ
	public List<Banking_group3> readBankingAccounts()	{
		return (List<Banking_group3>)bankingRepository.findAll();
	}
	
	//READ BY ID
	public List<Banking_group3> readBankingAccountsByUser(Long id)	{
		return (List<Banking_group3>)bankingRepository.findAllById(id);
	}
	
    // Get all account balances by Id
    public List<Float> getAllAccountBalancesById(Long Id) {
        List<Banking_group3> accounts = (List<Banking_group3>) bankingRepository.findAllById(Id);
        List<Float> accountBalances = accounts.stream()
                .map(Banking_group3::getBalance) // Extract the balance of each account
                .toList();
        return accountBalances;
    }
    
    //Get all account balances
    public List<Float> getAllAccountBalances() {
        List<Banking_group3> accounts = (List<Banking_group3>) bankingRepository.findAll();
        List<Float> accountBalances = accounts.stream()
                .map(Banking_group3::getBalance) // Extract the balance of each account
                .toList();
        return accountBalances;
    }
	

}

