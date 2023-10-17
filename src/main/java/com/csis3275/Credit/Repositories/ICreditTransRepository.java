package com.csis3275.Credit.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.csis3275.Credit.model.CreditTrans_group3;
import com.csis3275.banking.model.BankingTrans_group3;

@Repository
public interface ICreditTransRepository extends CrudRepository<CreditTrans_group3, Long> {
	
	@Query("SELECT ct FROM CreditTrans_group3 ct WHERE ct.credit.id = :accountId")
	List<CreditTrans_group3> findAllById(Long accountId);

}
