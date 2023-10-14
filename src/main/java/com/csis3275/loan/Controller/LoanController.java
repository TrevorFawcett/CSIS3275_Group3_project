package com.csis3275.loan.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.loan.model.LoanServiceImpl;
import com.csis3275.loan.model.Loan_group3;

@Controller
public class LoanController {
	
	@Autowired
	private LoanServiceImpl loanService;

	@GetMapping("/user-page/loan/add")
	public String addLoan(Model model) {
		model.addAttribute("loanToAdd", new Loan_group3());
		return "/loan/add";
	}

	@PostMapping("/user-page/loan/add")
	public String addNewLoan(Loan_group3 loanToAdd) {
		
		float payment;    

		if (loanToAdd.getLoan_type().equals("Car")) {
			loanToAdd.setRate(0.03f);

			
		} else if (loanToAdd.getLoan_type().equals("Home")) {
			loanToAdd.setRate(0.075f);

		}
		
		
		payment = (loanToAdd.getAmount() * (loanToAdd.getRate() * loanToAdd.getLoanTerm() ) );
		
		loanToAdd.setTotalToBePaid(payment + loanToAdd.getAmount());


		loanService.createLoans(loanToAdd);
		return "redirect:/user-page";
	}

}
