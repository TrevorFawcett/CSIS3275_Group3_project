package com.csis3275.login.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.login.model.FormData_group3;
import com.csis3275.login.model.PasswordResetForm_group3;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;

@Controller
public class PasswordController_group3 {

	@Autowired
	private UserServiceImpl_group3 userservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/password")
	public String passwordReset(Model model) {
		
		ArrayList<User_group3> listofusers = userservice.readUsers();
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i=0; i<listofusers.size();i++) {
			list.add(listofusers.get(i).getEmail());
		}
		
		
		model.addAttribute("userList",list);
		model.addAttribute("passwordData", new PasswordResetForm_group3());
		
		return "passwordreset";
	}
	
	//@PostMapping("/newUser") public String registerUser(@ModelAttribute("accountData") FormData_group3 dataset ,Model model){ 
	@PostMapping("passwordRequest")
	public String passwordCheck(@ModelAttribute("passwordData") PasswordResetForm_group3 dataset ,Model model) 
	{
		ArrayList<User_group3> listofusers = userservice.readUsers();
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i=0; i<listofusers.size();i++) {
			list.add(listofusers.get(i).getEmail());
		}
		
		
		model.addAttribute("userList",list);
		
		System.out.println(dataset.getUserName());
		System.out.println(dataset.comparePassword(dataset.getPassword1(),dataset.getPassword2()));
		
		ArrayList<String> errors = new ArrayList<String>();
		
		 if(dataset.comparePassword(dataset.getPassword1(),dataset.getPassword2()) != true) 
		 { 
			 errors.add("Error: Passwords do no match");
			 //model.addAttribute("error", "Error: Passwords do no match"); 
		 }
		 
		 if(dataset.getPassword1().length() < 8) {
			 errors.add("Password must be at least 8 characters");
		 }
		
		 if(errors.size() > 0) {
			 model.addAttribute("errors", errors); 
			 return "passwordreset";
		 }
		 else {
			 updatePassword(dataset);
			 return "redirect:/admin-page";
		 }
		
	} 
	
	public void updatePassword(PasswordResetForm_group3 dataset) {
		String email = dataset.getUserName();
		User_group3 userToUpdate = userservice.getUserByEmail(email);
		
		String newPassword = passwordEncoder.encode(dataset.getPassword1());
		userToUpdate.setPassword(newPassword);
		
		userservice.updateProfile(userToUpdate);
		
		
	}
	
	
}
