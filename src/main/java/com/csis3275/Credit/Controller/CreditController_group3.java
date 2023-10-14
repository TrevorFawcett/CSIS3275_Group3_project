package com.csis3275.Credit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.Credit.model.Credit_group3;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;

@Controller
public class CreditController_group3 {
	

	@Autowired
	private CreditServiceImpl creditService;
	
	@Autowired 
	private UserServiceImpl_group3 userService;
	

	@GetMapping("/user-page/credit/add")
	public String addCreditCardAccount(Model model) {
		model.addAttribute("creditToAdd", new Credit_group3());
		return "/credit/add";
	}

	@PostMapping("/user-page/credit/add")
	public String addNewCreditCardAccount(Credit_group3 creditToAdd) {

		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			
			String username = authentication.getName();
			User_group3 currentUser = userService.getUserByEmail(username);
			
			 creditToAdd.setUser(currentUser);

			
			if (creditToAdd.getType().equals("Cashback")) {

				creditToAdd.setTransfer(3000);
				creditToAdd.setBalance(0.0f);
				
			} else if (creditToAdd.getType().equals("Rewards")) {
				creditToAdd.setTransfer(5000);
				creditToAdd.setBalance(0.0f);
			}
		}
		
		

		creditService.createCreditCardAccounts(creditToAdd);
		return "redirect:/user-page";
	}

}
