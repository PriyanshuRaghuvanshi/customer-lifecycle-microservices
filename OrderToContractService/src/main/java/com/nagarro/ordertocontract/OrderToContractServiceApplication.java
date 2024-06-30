package com.nagarro.ordertocontract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nagarro.ordertocontract")
public class OrderToContractServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderToContractServiceApplication.class, args);
	}

}
