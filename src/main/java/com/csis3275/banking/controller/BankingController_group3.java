package com.csis3275.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.banking.model.Banking_group3;
import com.csis3275.banking.repositories.IBankingRepository;
import com.csis3275.banking.model.BankingServiceImpl;

@Controller
public class BankingController_group3 {
	
	
	@Autowired
	private BankingServiceImpl bankingService;

	
	@GetMapping("/user-page/banking/add")
	public String addBankingAccount(Model model) {
		model.addAttribute("bankingToAdd", new Banking_group3());
		return "banking/add";
	}
	
	@PostMapping("/user-page/banking/add")
	public String addNewBankingAccount(Banking_group3 bankingToAdd) {
		bankingService.createStudent(bankingToAdd);
		return "redirect:/students/list";
	}
	

}
