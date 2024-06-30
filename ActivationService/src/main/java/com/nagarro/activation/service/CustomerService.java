package com.nagarro.activation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.activation.exceptions.CustomException;
import com.nagarro.activation.external.services.ContractService;
import com.nagarro.activation.models.ActivationStatus;
import com.nagarro.activation.models.Contract;
import com.nagarro.activation.models.Customer;
import com.nagarro.activation.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
    private CustomerRepository  customerRepository ;
	
	@Autowired
    private ContractService contractService;

    public void saveCustomer(Customer customer) throws CustomException {
    	try {
    	customerRepository.save(customer);
    	} catch (Exception e) {
    	 throw new CustomException("Error saving customer: " + customer.getCustomerId(), e);
    	}
    }
    
    public void updateActivationStatus(String customerId, ActivationStatus activationStatus) throws CustomException {
        // Update activationStatus for the given customerId
        try {
        	  customerRepository.updateActivationStatus(customerId, activationStatus);
        	} catch (Exception e) {
        		throw new CustomException("Error updating activation status for customer: " + customerId, e);
        	}
    }

	public void updateContracts(Long msisdn ) throws CustomException {
		List<Contract>  contracts=  contractService.getContractsByMsisdn(msisdn);
		
		System.out.print("success");
		Optional<Customer> optionalCustomer = customerRepository.findByMsisdn(msisdn);
		    if (optionalCustomer.isPresent()) {
		        Customer customer = optionalCustomer.get();
		        customer.setContracts(contracts);
		        customerRepository.save(customer);
		    } else {
		        throw new CustomException("Customer not found with msisdn: " + msisdn);
		    }
	}
}
