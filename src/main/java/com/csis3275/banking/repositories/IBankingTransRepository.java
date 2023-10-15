package com.csis3275.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.csis3275.banking.model.BankingTrans_group3;

import jakarta.transaction.Transactional;

public interface IBankingTransRepository extends CrudRepository<BankingTrans_group3, Long> {

	@Query("SELECT bt FROM BankingTrans_group3 bt WHERE bt.banking.id = :accountId")
	List<BankingTrans_group3> findAllById(Long accountId);

	
}
