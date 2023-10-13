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
	public Banking_group3 createStudent(Banking_group3 newBanking)	{
		return bankingRepository.save(newBanking);
	}
	
	//READ
	public List<Banking_group3> readStudents()	{
		return (List<Banking_group3>)bankingRepository.findAll();
	}

}

