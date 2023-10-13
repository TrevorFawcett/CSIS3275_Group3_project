package com.csis3275.Credit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.Credit.model.Credit_group3;

@Controller
public class CreditController_group3 {
	

	@Autowired
	private CreditServiceImpl creditService;

	@GetMapping("/user-page/credit/add")
	public String addCreditCardAccount(Model model) {
		model.addAttribute("creditToAdd", new Credit_group3());
		return "/credit/add";
	}

	@PostMapping("/user-page/credit/add")
	public String addNewCreditCardAccount(Credit_group3 creditToAdd) {

		if (creditToAdd.getType().equals("Cashback")) {

			creditToAdd.setLimit(3000);
			creditToAdd.setBalance(0.0f);
			
		} else if (creditToAdd.getType().equals("Rewards")) {
			creditToAdd.setLimit(5000);
			creditToAdd.setBalance(0.0f);
		}

		creditService.createCreditCardAccounts(creditToAdd);
		return "redirect:/user-page";
	}

}
