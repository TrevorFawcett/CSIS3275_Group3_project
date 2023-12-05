package com.csis3275.loan.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.Credit.model.CreditTransServiceImpl;
import com.csis3275.Credit.model.CreditTrans_group3;
import com.csis3275.Credit.model.Credit_group3;
import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.banking.model.Banking_group3;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;

@Controller
public class LoanTransController_group3 {
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserServiceImpl_group3 userService;
	
	@Autowired
	CreditTransServiceImpl creditTransService;
	
	@Autowired
	CreditServiceImpl creditService;
	
	
	
	
	@GetMapping("user-page/loan/transactions")
	public String showTransactions(@RequestParam("account_id") String Id , Model model, Principal principal) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User_group3 currentUser = userService.getUserByEmail(username);
		
		model.addAttribute("creditTrans",creditTransService.readCreditTransactionsById(Long.parseLong(Id)) );
		model.addAttribute("userRole", currentUser.getRole());
		model.addAttribute("accountId", Id);
		
		return "loan/transactions";
	}

}
