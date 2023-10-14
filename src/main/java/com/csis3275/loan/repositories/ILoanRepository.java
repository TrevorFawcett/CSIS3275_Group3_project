package com.csis3275.loan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.csis3275.loan.model.Loan_group3;

@Repository
public interface ILoanRepository extends CrudRepository<Loan_group3, Long>{
	
	@Query("SELECT l FROM Loan_group3 l WHERE l.user.id = :userId")
	List<Loan_group3> findAllById(Long userId);

}
