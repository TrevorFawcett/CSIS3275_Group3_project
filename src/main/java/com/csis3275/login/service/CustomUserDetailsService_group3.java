package com.csis3275.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.csis3275.login.model.User_group3;

import com.csis3275.login.repositories.UserRepository_group3;

@Service
public class CustomUserDetailsService_group3 implements UserDetailsService {
	
	 @Autowired
	 private UserRepository_group3 userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User_group3 user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return new CustomUserDetail_group3(user);

	}

}
