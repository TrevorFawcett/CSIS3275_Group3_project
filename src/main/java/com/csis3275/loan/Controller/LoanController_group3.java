package com.csis3275.loan.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.loan.model.LoanServiceImpl;
import com.csis3275.loan.model.Loan_group3;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;

@Controller
public class LoanController_group3 {
	
	@Autowired
	private LoanServiceImpl loanService;
	
	@Autowired 
	private UserServiceImpl_group3 userService;

	@GetMapping("/user-page/loan/add")
	public String addLoan(Model model) {
		model.addAttribute("loanToAdd", new Loan_group3());
		return "/loan/add";
	}

	@PostMapping("/user-page/loan/add")
	public String addNewLoan(Loan_group3 loanToAdd) {
		
		
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			
			String username = authentication.getName();
			User_group3 currentUser = userService.getUserByEmail(username); 
			
			
			loanToAdd.setUser(currentUser);
			
			float payment;    

			if (loanToAdd.getType().equals("Car")) {
				loanToAdd.setRate(0.03f);

				
			} else if (loanToAdd.getType().equals("Home")) {
				loanToAdd.setRate(0.075f);

			}
			
			
			payment = (loanToAdd.getAmount() * (loanToAdd.getRate() * loanToAdd.getLoanTerm() ) );
			
			loanToAdd.setTotalToBePaid(payment + loanToAdd.getAmount());

			
		
		
		
		}
		


		loanService.createLoans(loanToAdd);
		return "redirect:/user-page";
	}

}
