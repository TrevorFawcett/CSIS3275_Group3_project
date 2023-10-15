package com.csis3275.banking.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.banking.model.BankingTransServiceImpl;
import com.csis3275.banking.repositories.IBankingTransRepository;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;




@Controller
public class BankingTransController_group3 {
	
	
	@Autowired
	BankingTransServiceImpl bankingService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserServiceImpl_group3 userService;
	
	@GetMapping("user-page/banking/transactions")
	public String showTrans(@RequestParam("account_id") String Id , Model model, Principal principal) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User_group3 currentUser = userService.getUserByEmail(username);
		
		model.addAttribute("bankingTrans",bankingService.readBankingTransactionsById(Long.parseLong(Id)) );
		model.addAttribute("userRole", currentUser.getRole());
		
		
		return "banking/transactions";
		
	}

}
