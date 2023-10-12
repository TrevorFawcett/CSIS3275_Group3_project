package com.csis3275.login.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csis3275.login.model.User_group3;

@Repository
public interface UserRepository_group3 extends CrudRepository<User_group3, Long> {
    
	User_group3 findByEmail (String email);
}
