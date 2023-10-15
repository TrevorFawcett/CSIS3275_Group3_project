package com.csis3275.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.banking.model.Banking_group3;
import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.banking.repositories.IBankingRepository;
import com.csis3275.banking.repositories.IBankingTransRepository;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;
import com.csis3275.banking.model.BankingServiceImpl;

@Controller
public class BankingController_group3 {

	@Autowired
	private BankingServiceImpl bankingService;
	
	@Autowired 
	private UserServiceImpl_group3 userService;
	
	
	@Autowired
	private IBankingTransRepository transRepo;

	@GetMapping("/user-page/banking/add")
	public String addBankingAccount(Model model) {
		model.addAttribute("bankingToAdd", new Banking_group3());
		return "/banking/add";
	}
	
	
	@GetMapping("/user-page/banking/delete")
	public String deleteBankingAccount(Model model, @RequestParam("account_id") Long Id) {
		
		Banking_group3 banking = bankingService.readSingleBankingAccount(Id);
		//transRepo.deleteAllById(Id);
		
		if(banking.getBalance() <= 0 ) {
			bankingService.DeleteAccount(Id);
		}
		
		
		
		return "redirect:/admin-page";
	}
	

	@PostMapping("/user-page/banking/add")
	public String addNewBankingAccount(Banking_group3 bankingToAdd) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

				if (authentication != null) {
					
					String username = authentication.getName();
					User_group3 currentUser = userService.getUserByEmail(username);
					
					 bankingToAdd.setUser(currentUser);
					
					if (bankingToAdd.getType().equals("Checking")) {

						bankingToAdd.setTransferLimit(1000);
						bankingToAdd.setBalance(0.0f);
					} else if (bankingToAdd.getType().equals("Savings")) {
						bankingToAdd.setTransferLimit(2000);
						bankingToAdd.setBalance(0.0f);
					}
					
				}
		

		bankingService.createBankingAccounts(bankingToAdd);
		return "redirect:/user-page";
	}
	
	

}
