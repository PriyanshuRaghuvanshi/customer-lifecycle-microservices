package com.nagarro.activation.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.activation.exceptions.CustomException;
import com.nagarro.activation.external.services.ContractService;
import com.nagarro.activation.models.ActivationStatus;
import com.nagarro.activation.models.Contract;
import com.nagarro.activation.models.Customer;
import com.nagarro.activation.publisher.RabbitMQProducer;
import com.nagarro.activation.service.CustomerService;

@RestController
@RequestMapping("activation/v1")
public class ActivationController {

	
    private CustomerService customerService;

    private RabbitMQProducer producer;
    
    
    

    private static final Logger logger = LoggerFactory.getLogger(ActivationController.class);
    
    
    @Autowired
    public ActivationController(CustomerService customerService, RabbitMQProducer producer ) {
		this.customerService = customerService;
		this.producer = producer;
		
		
	}

    
    
	@PostMapping("/customer")
//	@PreAuthorize("hasAnyAuthority('CANNOT_ACCESS_NOTIFICATION')")
    public ResponseEntity<String> saveUser( @RequestBody Customer customer ) {
		try {

         customer.setActivationStatus(ActivationStatus.IN_PROGRESS);
         customerService.saveCustomer(customer);
         producer.sendMessageToVerificationService(customer);
        return ResponseEntity.ok("User saved successfully");
        
		} catch (CustomException  ce) {
    	     logger.error("ActivationException caught: {}", ce.getMessage(), ce);
   	         return ResponseEntity.status(404).body("Activation failure");
         } catch (Exception e) {
             logger.error("Exception caught: {}", e.getMessage(), e);
             return ResponseEntity.status(404).body("Activation failure");
         }
}
	
	 @PatchMapping("/customer/contracts/{msisdn}")
	    public ResponseEntity<?> updateContracts(@PathVariable Long msisdn) throws CustomException {
//		 List<Contract>  contracts=  contractService.getContractsByMsisdn(msisdn);
//		 logger.info("success");
		 
		 customerService.updateContracts(msisdn);
	        return ResponseEntity.ok().build();
	    }
}
