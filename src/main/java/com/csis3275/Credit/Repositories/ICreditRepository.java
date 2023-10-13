package com.csis3275.Credit.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csis3275.Credit.model.Credit_group3;

@Repository
public interface ICreditRepository extends CrudRepository<Credit_group3, Long> {

}
