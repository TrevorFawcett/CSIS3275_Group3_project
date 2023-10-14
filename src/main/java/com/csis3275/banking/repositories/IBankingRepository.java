package com.csis3275.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csis3275.banking.model.Banking_group3;


@Repository
public interface IBankingRepository extends CrudRepository<Banking_group3, Long> {

	@Query("SELECT b FROM Banking_group3 b WHERE b.user.id = :userId")
	List<Banking_group3> findAllById(Long userId);

}
