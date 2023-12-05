package com.csis3275.login.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.Credit.model.CreditTransServiceImpl;
import com.csis3275.Credit.model.CreditTrans_group3;
import com.csis3275.Credit.model.Credit_group3;
import com.csis3275.banking.model.BankingServiceImpl;
import com.csis3275.banking.model.Banking_group3;
import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.banking.model.BankingTransServiceImpl;

import com.csis3275.loan.model.LoanServiceImpl;

import com.csis3275.login.model.FormData_group3;

import com.csis3275.login.model.UserDto_group3;
import com.csis3275.login.model.User_group3;

import com.csis3275.login.service.UserServiceImpl_group3;
import com.csis3275.login.service.UserService_group3;

@Controller
public class UserController_group3 {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserServiceImpl_group3 repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BankingServiceImpl bankingService;

	@Autowired
	private CreditServiceImpl creditService;

	@Autowired
	private LoanServiceImpl loanService;

	@Autowired
	private CreditTransServiceImpl creditTransService;

	@Autowired
	private BankingTransServiceImpl bankingTransService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("user-page")
	public String userPage(Model model, Principal principal) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User_group3 currentUser = repository.getUserByEmail(username);

		// Get all account balances
		List<Float> accountBalances = bankingService.getAllAccountBalancesById(currentUser.getId());
		List<Float> creditBalance = creditService.getAllAccountBalancesById(currentUser.getId());
		List<Float> loanBalance = loanService.getAllAccountBalancesById(currentUser.getId());

		// LIST CREDIT AND Banking
		List<Object> allAccounts = new ArrayList<>();
		allAccounts.addAll(bankingService.readBankingAccountsByUser(currentUser.getId()));
		allAccounts.addAll(creditService.readCreditCardAccountsById(currentUser.getId()));

		// Calculate total balance
		float totalBalance = (float) accountBalances.stream().mapToDouble(Float::doubleValue).sum();
		float totalCreditBalance = (float) creditBalance.stream().mapToDouble(Float::doubleValue).sum();
		float totalLoanBalance = (float) loanBalance.stream().mapToDouble(Float::doubleValue).sum();

		model.addAttribute("user", userDetails);

		// banking details
//		model.addAttribute("error","");
//		model.addAttribute("errorCredit", "");
		model.addAttribute("banking", bankingService.readBankingAccountsByUser(currentUser.getId()));
		model.addAttribute("totalBalance", totalBalance); // Add total balance to the model

		// Credit Card Details
		model.addAttribute("credit", creditService.readCreditCardAccountsById(currentUser.getId()));
		model.addAttribute("totalCreditBalance", totalCreditBalance); // Add total balance to the model

		// Loan Details
		model.addAttribute("loan", loanService.readLoansById(currentUser.getId()));
		model.addAttribute("totalLoanBalance", totalLoanBalance);

		// Accounts details
		model.addAttribute("userAccounts", allAccounts);

		return "user";
	}

	@PostMapping("/user-page/transfer")
	public String handleFormSubmission(Model model, RedirectAttributes redirectAttributes,
									   @RequestParam("fromAccount") String fromAccount,
	                                   @RequestParam("toAccount") String toAccount,
	                                   @RequestParam("amount") String amount) {
		
		
		
		
		// Split the fromAccount and toAccount values into ID and name
	    String[] fromAccountParts = fromAccount.split(",");
	    String fromAccountId = fromAccountParts[0];
	    String fromAccountName = fromAccountParts[1];

	    String[] toAccountParts = toAccount.split(",");
	    String toAccountId = toAccountParts[0];
	    String toAccountName = toAccountParts[1];
	    
	    float amt = Float.parseFloat(amount);
	    Long toActId = Long.parseLong(toAccountId);
	    Long fromActId = Long.parseLong(fromAccountId);

	   
	    
	    if (fromAccountName.equals(toAccountName)) {
	    	  redirectAttributes.addFlashAttribute("error", "from and to accounts are the same");
	    	  //System.out.println("from and to accounts are the same");
	    }else if(fromAccountName.equals("Checking Account") || fromAccountName.equals("Savings Account") ) {
	    	
	    	System.out.println(fromAccountName);
	    	Banking_group3 banking = bankingService.readSingleBankingAccount(fromActId);
	    	Credit_group3 credit = creditService.readSingleCreditAccount(toActId);
		
			
			
			
			if (toAccountName.equals("Checking Account") || toAccountName.equals("Savings Account")) {
				Banking_group3 bankingReciever = bankingService.readSingleBankingAccount(toActId);
				bankingTransService.createBankingTransactionTransfer("reciever", amt, bankingReciever);
			}else {
				creditTransService.createCreditTransactionTransfer("reciever", amt, credit);
			}
			
			bankingTransService.createBankingTransactionTransfer("sender", amt, banking);
	    	
	    }else if (fromAccountName.equals("Rewards Card") || fromAccountName.equals("Cashback Card") ) {
	    	
	    	if(toAccountName.equals("Rewards Card") || toAccountName.equals("Cashback Card")){
	    		redirectAttributes.addFlashAttribute("errorCredit", "It is forbidden to transfer between two credit accounts");
	    		//System.out.println("It is forbidden to transfer between two credit accounts");
	    	}else {
	    		
	    	
	    	Banking_group3 banking = bankingService.readSingleBankingAccount(toActId);
	    	Credit_group3 credit = creditService.readSingleCreditAccount(fromActId);
	    	
			bankingTransService.createBankingTransactionTransfer("reciever", amt, banking);
			creditTransService.createCreditTransactionTransfer("sender", amt, credit);
			
	    	}
	    	
	    }
	    		
	    return "redirect:/user-page"; // Redirect to the user-page after form submission
	}

	@GetMapping("admin-page")
	public String adminPage(Model model, Principal principal) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		model.addAttribute("userInfo", repository.readUsers());
		model.addAttribute("bankingInfo", bankingService.readBankingAccounts());
		model.addAttribute("creditInfo", creditService.readCreditCardAccounts());
		model.addAttribute("loanInfo", loanService.readLoans());

		return "admin";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("accountData", new FormData_group3());
		return "register";
	}

	@PostMapping("/newUser")
	public String registerUser(@ModelAttribute("accountData") FormData_group3 dataset, Model model) {

		User_group3 newUser = new User_group3(dataset.getEmail(), passwordEncoder.encode(dataset.getPassword()), "USER",
				dataset.getFirstName());
		newUser.setLastName(dataset.getLastName());
		repository.createUser(newUser);

		return "redirect:/";
	}

	@GetMapping("/profile")
	public String userProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		User_group3 currentUser = repository.getUserByEmail(username);

		model.addAttribute("accountInfo", currentUser);

		return "user-profile";
	}

	@PostMapping("/profileEdit")
	public String profielEdit(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		User_group3 currentUser = repository.getUserByEmail(username);

		model.addAttribute("editInfo", currentUser);

		return "user-profile-edit";
	}

	@PostMapping("/change")
	public String accountEditUpdate(@ModelAttribute User_group3 updateUser, Model model) {

		repository.updateProfile(updateUser);

		return "redirect:/profile";
	}

}