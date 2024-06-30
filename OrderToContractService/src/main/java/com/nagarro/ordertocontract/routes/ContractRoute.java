package com.nagarro.ordertocontract.routes;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.nagarro.ordertocontract.models.Contract;
import com.nagarro.ordertocontract.models.Order;
import com.nagarro.ordertocontract.service.ContractService;


@Component
public class ContractRoute extends RouteBuilder {
	
	private final ContractService contractService;

    @Autowired
    public ContractRoute(ContractService contractService) {
        this.contractService = contractService;
    }
	
	
    @Override
    public void configure() throws Exception {
    	
    	restConfiguration().component("servlet").port(8092).host("localhost").bindingMode(RestBindingMode.json);
    	    	 	
    	rest()
        .post("/contracts")
        .type(Order.class)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .to("direct:contractCreation");
    	
    	from("direct:contractCreation")
            .log("Received Order: ${body}")
            .process(exchange -> {
                Order order = exchange.getIn().getBody(Order.class);
                Contract contract = createContractFromOrder(order);
                
                exchange.getIn().setBody(contract);
                
            })
            .log("Created Contract: ${body}");
          
   }

    private Contract createContractFromOrder(Order order) {
    	 Contract contract = new Contract();
         // Set contract properties from the order
         
        
         contract.setMsisdn(order.getMsisdn());
         contract.setRateplan(order.getRateplan());
         contract.setRateplanId(order.getRateplanID());
         contractService.saveContract(contract);
         return contract;
        
    }
    
}

