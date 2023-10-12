package com.csis3275.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.csis3275.login.model.UserAccountDto_group3;
import com.csis3275.login.model.UserAccount_group3;

import com.csis3275.login.repositories.AccountRepository_group3;

@Service
public class AccountServiceImpl_group3 implements AccountService_group3 {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository_group3 accountRepository;

	@Override
	public UserAccount_group3 save(UserAccountDto_group3 accountDto) {
		UserAccount_group3 account = new UserAccount_group3(accountDto.getEmail(), passwordEncoder.encode(accountDto.getPassword()) , accountDto.getFirstName(), 
				accountDto.getLastName(),accountDto.getAccountNumber());
		return accountRepository.save(account);
	}
	
	//CREATE
		public UserAccount_group3 createAccount(UserAccountDto_group3 accountDto)	{
			UserAccount_group3 account = new UserAccount_group3(accountDto.getEmail(), passwordEncoder.encode(accountDto.getPassword()) , accountDto.getFirstName(),
					accountDto.getLastName(),accountDto.getAccountNumber());
				return accountRepository.save(account);
		}
		
		
	
}
