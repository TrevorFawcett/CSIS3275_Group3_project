package com.csis3275.login.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csis3275.login.model.UserAccount_group3;
import com.csis3275.login.model.User_group3;

@Repository
public interface AccountRepository_group3 extends CrudRepository<UserAccount_group3, Long> {

	//UserAccount_group3 findByEmail (String email);
}
