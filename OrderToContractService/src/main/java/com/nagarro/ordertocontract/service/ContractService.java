package com.nagarro.ordertocontract.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ordertocontract.models.*;
import com.nagarro.ordertocontract.repo.ContractRepo;

@Service
public class ContractService {

	 @Autowired
	   private ContractRepo contractRepo;
	 
	 
	 public void saveContract(Contract contract) {
	        contractRepo.save(contract);
	    }


	public List<Contract> getContractsByMsisdn(Long msisdn) {
		 return contractRepo.findByMsisdn(msisdn);
	    
	}
}
