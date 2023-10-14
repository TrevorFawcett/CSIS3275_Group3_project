package com.csis3275.Credit.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csis3275.Credit.model.Credit_group3;
import com.csis3275.banking.model.Banking_group3;

@Repository
public interface ICreditRepository extends CrudRepository<Credit_group3, Long> {
	
	@Query("SELECT c FROM Credit_group3 c WHERE c.user.id = :userId")
	List<Credit_group3> findAllById(Long userId);

}
