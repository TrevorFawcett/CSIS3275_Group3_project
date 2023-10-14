package com.csis3275.login.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.banking.model.BankingServiceImpl;
import com.csis3275.loan.model.LoanServiceImpl;
import com.csis3275.login.model.AccountGenerator_group3;
import com.csis3275.login.model.FormData_group3;
import com.csis3275.login.model.UserAccountDto_group3;
import com.csis3275.login.model.UserDto_group3;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.AccountServiceImpl_group3;
import com.csis3275.login.service.AccountService_group3;
import com.csis3275.login.service.UserServiceImpl_group3;
import com.csis3275.login.service.UserService_group3;




@Controller
public class UserController_group3 {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserServiceImpl_group3 repository;
	
	@Autowired
	private AccountServiceImpl_group3 accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BankingServiceImpl bankingService;
	
	@Autowired 
	private CreditServiceImpl creditService;
	
	@Autowired
	private LoanServiceImpl loanService;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("user-page")
	public String userPage (Model model, Principal principal) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		User_group3 currentUser = repository.getUserByEmail(username);
		 
		
		 // Get all account balances
        List<Float> accountBalances = bankingService.getAllAccountBalancesById(currentUser.getId());
        List<Float> creditBalance = creditService.getAllAccountBalancesById(currentUser.getId());
        List<Float> loanBalance = loanService.getAllAccountBalancesById(currentUser.getId());


        // Calculate total balance
        float totalBalance = (float) accountBalances.stream().mapToDouble(Float::doubleValue).sum();
        float totalCreditBalance = (float) creditBalance.stream().mapToDouble(Float::doubleValue).sum();
        float totalLoanBalance = (float)loanBalance.stream().mapToDouble(Float::doubleValue).sum();
        
		model.addAttribute("user", userDetails);
		
		//banking details
		model.addAttribute("banking", bankingService.readBankingAccountsByUser(currentUser.getId()));
		model.addAttribute("totalBalance", totalBalance); // Add total balance to the model
		
		//Credit Card Details
		model.addAttribute("credit", creditService.readCreditCardAccountsById(currentUser.getId()));
		model.addAttribute("totalCreditBalance", totalCreditBalance); // Add total balance to the model
		
		
		//Loan Details
		model.addAttribute("loan", loanService.readLoansById(currentUser.getId()));
		model.addAttribute("totalLoanBalance", totalLoanBalance);
		
		return "user";
	}
	
	
	
	
	@GetMapping("admin-page")
	public String adminPage (Model model, Principal principal) {
		
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		model.addAttribute("userInfo", repository.readUsers() );
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

	@PostMapping("/newUser") public String registerUser(@ModelAttribute("accountData") FormData_group3 dataset ,Model model){ 
		AccountGenerator_group3 gen = new AccountGenerator_group3();
		ArrayList<User_group3> userList = repository.readUsers();
		
		
		Long x;
		do {
		x = gen.generateNumber();
		}while(gen.checkDB(userList, x));
		
		
		
		UserAccountDto_group3 newAccount = new UserAccountDto_group3(dataset.getEmail(),dataset.getPassword(),dataset.getFirstName(),
				dataset.getLastName(),x);
		accountService.createAccount(newAccount);
		//email, password(hash), "USER", accountNumber
		User_group3 newUser = new User_group3(newAccount.getEmail(),passwordEncoder.encode(newAccount.getPassword()),"USER",newAccount.getAccountNumber());
		repository.createUser(newUser);
		
		
		return "redirect:/";
	}
	
}