package com.nagarro.ordertocontract.controller;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ordertocontract.models.Contract;
import com.nagarro.ordertocontract.models.Order;
import com.nagarro.ordertocontract.service.ContractService;


@RestController
@RequestMapping("/contracts/v1")
public class ContractController {

    @Autowired
    private ProducerTemplate producerTemplate;
    
    @Autowired
    private ContractService contractService;
    

    @PostMapping("/invokeContractCreation")
    public ResponseEntity<String> invokeContractCreation(@RequestBody Order order) {
        producerTemplate.sendBody("direct:contractCreation", order);
        return ResponseEntity.ok("Contract creation invoked successfully");
    }
    
    
    
    @GetMapping("/{msisdn}")
    public ResponseEntity<List<Contract>> getContractsByMsisdn(@PathVariable Long msisdn) {
        List<Contract> contracts = contractService.getContractsByMsisdn(msisdn);
        return ResponseEntity.ok(contracts);
    }
}

