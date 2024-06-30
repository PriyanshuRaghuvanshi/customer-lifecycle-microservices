package com.nagarro.ordertocontract.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.ordertocontract.routes.ContractRoute;
import com.nagarro.ordertocontract.service.ContractService;

@Configuration
public class CamelConfig {

	private final ContractService contractService;

    @Autowired
    public CamelConfig(ContractService contractService) {
        this.contractService = contractService;
    }
    public RouteBuilder contractRoute() {
        return new ContractRoute(contractService);
    }

    // Other configuration beans...
    
    @Bean
    public CamelContext camelContext() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        
        camelContext.addRoutes(contractRoute());
        camelContext.start();
        return camelContext;
    }
    
}

