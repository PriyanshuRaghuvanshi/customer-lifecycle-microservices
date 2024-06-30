package com.nagarro.activation.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.nagarro.activation.models.Contract;

@FeignClient(name = "contract-service", url = "http://localhost:8092")
public interface ContractService {

	 @GetMapping("/contracts/v1/{msisdn}")
	 public List<Contract> getContractsByMsisdn(@PathVariable("msisdn") Long msisdn) ;
    
}