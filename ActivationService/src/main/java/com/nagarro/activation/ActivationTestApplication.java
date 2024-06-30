package com.nagarro.activation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ActivationTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivationTestApplication.class, args);
	}

}
