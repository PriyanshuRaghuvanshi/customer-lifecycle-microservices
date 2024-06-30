package com.nagarro.activation.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig{


	  @Value("${jwt.auth.converter.resource-id}")
	  private String resourceId;
	  
//	  protected void configure(HttpSecurity httpSecurity) throws Exception {
//	        httpSecurity
//	        	.authorizeRequests()
//	        	.requestMatchers("/")
//	        	.permitAll();
//	    }
	  
	  @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         
		  http.authorizeHttpRequests(auth -> {
        
        	 auth.requestMatchers("/activation/v1/**").fullyAuthenticated();
        	        
                })
                .oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults()));
          

        return http.build();
    }

	@Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
           
        	Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

        
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, List<String>> clientRoleMap = objectMapper.convertValue(
                    resourceAccess.get(resourceId),
                    new TypeReference<Map<String, List<String>>>() {}
            );
            
            List<String> clientRoles = new ArrayList<>();
           
            if (clientRoleMap != null && clientRoleMap.containsKey("roles")) {
                clientRoles.addAll(clientRoleMap.get("roles"));
            } else {
                // Log a warning or take other appropriate actions
                // Or can set an empty list of roles
                System.out.println("Warning: Client roles not present in the token");
            }
            return clientRoles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
	

}
