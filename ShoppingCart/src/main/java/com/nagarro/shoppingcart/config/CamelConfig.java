package com.nagarro.shoppingcart.config;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

	@Configuration
	public class CamelConfig {

	   
	    
	    @Bean
	    public CamelContext camelContext() throws Exception {
	        CamelContext camelContext = new DefaultCamelContext();
	        
	        
	        // Add more routes as needed
	        camelContext.start();
	        return camelContext;
	    }
	}


