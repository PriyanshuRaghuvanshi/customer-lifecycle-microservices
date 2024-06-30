package com.nagarro.shoppingcart.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.nagarro.shoppingcart.model.Order;

@FeignClient(name = "contract-service", url = "http://localhost:8092/contracts/v1")
public interface ContractService {

    @PostMapping("/contracts")
    void createContract(Order order);
}
