package com.csis3275.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class BankingTransController_group3 {
	
	
	@GetMapping("/user-page/banking/transactions")
	public String showTrans(@RequestParam("account_id") String Id , Model model) {
		
		model.addAttribute("bankingTrans", );
		
		return "";
		
	}

}
