package com.csis3275.banking.controller;

import java.security.Principal;
import java.util.Optional;

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

import com.csis3275.banking.model.BankingServiceImpl;
import com.csis3275.banking.model.BankingTransServiceImpl;
import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.banking.model.Banking_group3;
import com.csis3275.banking.repositories.IBankingRepository;
import com.csis3275.banking.repositories.IBankingTransRepository;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;




@Controller
public class BankingTransController_group3 {
	
	
	@Autowired
	BankingTransServiceImpl bankingTransService;
	
	@Autowired
	BankingServiceImpl bankingService;
	
	@Autowired
	IBankingRepository bankingRepository;
	
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
		
		model.addAttribute("bankingTrans",bankingTransService.readBankingTransactionsById(Long.parseLong(Id)) );
		model.addAttribute("userRole", currentUser.getRole());
		model.addAttribute("accountId", Id);
		
		
		
		return "banking/transactions";
		
	}
	
	@GetMapping("user-page/banking/transactions/add")
	public String showAddTransaction(Model model, @RequestParam("account_id") String Id) {

	
		model.addAttribute("transToAdd", new BankingTrans_group3());
		model.addAttribute("Id", Id);
		
		
		return "banking/addTrans";
	}
	
	@PostMapping("user-page/banking/transactions/add")
	public String addTransaction(BankingTrans_group3 newTran, @RequestParam("bank_id") Long Id) {
		
			Banking_group3 banking = bankingService.readSingleBankingAccount(Id);
			newTran.setBanking(banking);
	
			
			if ( newTran.getType().equals("withdrawal") || newTran.getType().equals("expense")) {
				newTran.setWithdrawal(newTran.getAmount());
				newTran.setDeposit(0.0f);
				banking.setBalance((float) (banking.getBalance() - newTran.getAmount()));
			} else if (newTran.getType().equals("deposit")) {
				
				newTran.setWithdrawal(0.0f);
				newTran.setDeposit(newTran.getAmount());
				banking.setBalance((float) (banking.getBalance() + newTran.getAmount()));
			}
		
			newTran.setRefunded(false);
			bankingTransService.createBankingTransaction(newTran);
			return "redirect:/user-page";
		
	}
	
	
	@GetMapping("/user-page/banking/transactions/refund")
	public String refundTransaction(
	    @RequestParam("account_id") Long accountId,
	    @RequestParam("transaction_id") Long transactionId) {
		
		BankingTrans_group3 b = bankingTransService.readSingleBankingTransaction(transactionId);
		Banking_group3 banking = bankingService.readSingleBankingAccount(accountId);
		
		banking.setBalance(banking.getBalance() + b.getAmount()); //UPDATE THE BALANCE
	    
		//RECORD THE NEW TRANSACTION
		
		BankingTrans_group3 newT = new BankingTrans_group3();
		newT.setType("Refund");
		newT.setDescription("refund transaction");
		newT.setWithdrawal(0.0f);
		newT.setDeposit(b.getAmount());
		newT.setAmount(b.getAmount());
		newT.setBanking(banking);
		newT.setRefunded(true);
		
		
		bankingTransService.createBankingTransaction(newT);
		
		
	    return "redirect:/admin-page"; 
	}
	
}
