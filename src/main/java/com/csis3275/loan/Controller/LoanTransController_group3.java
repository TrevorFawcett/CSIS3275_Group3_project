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
import com.csis3275.loan.model.LoanServiceImpl;
import com.csis3275.loan.model.LoanTransServiceImpl;
import com.csis3275.loan.model.LoanTrans_group3;
import com.csis3275.loan.model.Loan_group3;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;

@Controller
public class LoanTransController_group3 {
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserServiceImpl_group3 userService;
	
	@Autowired
	LoanTransServiceImpl loanTransService;
	//CreditTransServiceImpl creditTransService;
	
	@Autowired
	LoanServiceImpl loanService;
	//CreditServiceImpl creditService;
	
	
	
	
	@GetMapping("user-page/loan/transactions")
	public String showTransactions(@RequestParam("account_id") String Id , Model model, Principal principal) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User_group3 currentUser = userService.getUserByEmail(username);
		
		model.addAttribute("loanTrans",loanTransService.readLoanTransactionsById(Long.parseLong(Id)) );
		//model.addAttribute("creditTrans",creditTransService.readCreditTransactionsById(Long.parseLong(Id)) );
		model.addAttribute("userRole", currentUser.getRole());
		model.addAttribute("accountId", Id);
		
		return "loan/transactions";
	}
	
	
	@GetMapping("user-page/loan/transactions/add")
	public String showAddTransaction(Model model, @RequestParam("account_id") String Id) {

	
		model.addAttribute("transToAdd", new LoanTrans_group3());
		model.addAttribute("Id", Id);
		
		
		return "loan/addTrans";
	}
	
	
	@PostMapping("user-page/loan/transactions/add")
	public String addCreditTransaction(LoanTrans_group3 newTran, @RequestParam("loan_id") Long Id) {
		
		
		//Credit_group3 credit = creditService.readSingleCreditAccount(Id);
		Loan_group3 loan = loanService.readSingleLoanAccount(Id);
		newTran.setLoan(loan);
		
		
		
		if (newTran.getType().equals("payment"))
		{
			float transAmount = newTran.getAmount();
			loan.updateBalance(transAmount);
			
			
		}
	
		newTran.setRefunded(false);
		loanTransService.createLoanTransaction(newTran);
		
		
		return "redirect:/user-page";
		
		
		
	}

}
