package com.csis3275.loan.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.csis3275.loan.model.Loan_group3;

@Repository
public interface ILoanRepository extends CrudRepository<Loan_group3, Long>{

}
