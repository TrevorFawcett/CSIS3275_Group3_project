package com.csis3275.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.banking.model.BankingTransServiceImpl;
import com.csis3275.banking.repositories.IBankingTransRepository;




@Controller
public class BankingTransController_group3 {
	
	
	@Autowired
	BankingTransServiceImpl bankingService;
	
	@GetMapping("user-page/banking/transactions")
	public String showTrans(@RequestParam("account_id") String Id , Model model) {
		
		model.addAttribute("bankingTrans",bankingService.readBankingTransactionsById(Long.parseLong(Id)) );
		
		return "banking/transactions";
		
	}

}
