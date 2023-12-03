package com.csis3275.login.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.csis3275.login.model.UserDto_group3;
import com.csis3275.login.model.User_group3;
import com.csis3275.login.repositories.UserRepository_group3;




@Service
public class UserServiceImpl_group3 implements UserService_group3 {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository_group3 userRepository;

	public User_group3 updateProfile(User_group3 data) {
		
		return userRepository.save(data);
		
	}

	@Override
	public User_group3 save(User_group3 user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//READ
		public ArrayList<User_group3> readUsers()	{
			return (ArrayList<User_group3>)userRepository.findAll();
		}
	
	//CREATE
		public User_group3 createUser(User_group3 newUser)	{
			return userRepository.save(newUser);
		}
		
		
		public User_group3 getUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

		
		

}
