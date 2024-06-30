package com.nagarro.ordertocontract.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ordertocontract.models.Contract;



public interface ContractRepo extends JpaRepository<Contract, Long> {

	List<Contract> findByMsisdn(Long msisdn);

}
