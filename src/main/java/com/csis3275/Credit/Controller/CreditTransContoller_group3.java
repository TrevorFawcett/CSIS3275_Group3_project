package com.csis3275.Credit.Controller;

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
public class CreditTransContoller_group3 {
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserServiceImpl_group3 userService;
	
	@Autowired
	CreditTransServiceImpl creditTransService;
	
	@Autowired
	CreditServiceImpl creditService;
	
	
	
	
	@GetMapping("user-page/credit/transactions")
	public String showTransactions(@RequestParam("account_id") String Id , Model model, Principal principal) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User_group3 currentUser = userService.getUserByEmail(username);
		
		model.addAttribute("creditTrans",creditTransService.readCreditTransactionsById(Long.parseLong(Id)) );
		model.addAttribute("userRole", currentUser.getRole());
		model.addAttribute("accountId", Id);
		
		return "credit/transactions";
	}
	
	
	@GetMapping("user-page/credit/transactions/add")
	public String showAddTransaction(Model model, @RequestParam("account_id") String Id) {

	
		model.addAttribute("transToAdd", new CreditTrans_group3());
		model.addAttribute("Id", Id);
		
		
		return "credit/addTrans";
	}
	
	
	@PostMapping("user-page/credit/transactions/add")
	public String addCreditTransaction(CreditTrans_group3 newTran, @RequestParam("credit_id") Long Id) {
		
		
		Credit_group3 credit = creditService.readSingleCreditAccount(Id);
		newTran.setCredit(credit);
		
		
		if ( newTran.getType().equals("Purchase")) {
			credit.setBalance((float) (credit.getBalance() + newTran.getAmount()));
			
		} else if (newTran.getType().equals("payment")) {
			
			credit.setBalance((float) (credit.getBalance() - newTran.getAmount()));
		}
	
		newTran.setRefunded(false);
		creditTransService.createCreditTransaction(newTran);
		
		
		return "redirect:/user-page";
		
		
		
	}
	
	
	@GetMapping("/user-page/credit/transactions/refund")
	public String refundTransaction(
	    @RequestParam("account_id") Long accountId,
	    @RequestParam("transaction_id") Long transactionId) {
		
		CreditTrans_group3 c = creditTransService.readSingleCreditTransaction(transactionId);
		Credit_group3 credit = creditService.readSingleCreditAccount(accountId);
		
		credit.setBalance(credit.getBalance() - c.getAmount()); //UPDATE THE BALANCE
		c.setRefunded(true); 
	    
		//RECORD THE NEW TRANSACTION
		
		CreditTrans_group3 newT = new CreditTrans_group3();
		newT.setType("Refund");
		newT.setDescription("refund transaction");
		newT.setAmount(c.getAmount());
		newT.setRefunded(true);
		
		creditTransService.createCreditTransaction(newT);
		
		
	    return "redirect:/admin-page"; 
	}
	


}
