package com.csis3275.banking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csis3275.banking.model.Banking_group3;


@Repository
public interface IBankingRepository extends CrudRepository<Banking_group3, Long> {

}
