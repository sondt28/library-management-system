package com.son.librarymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/**").permitAll();
		http.cors().configurationSource(request -> 
						new CorsConfiguration().applyPermitDefaultValues());
		http.csrf().disable();
		
		return http.build();
	}
	
}
