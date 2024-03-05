package com.rest.webservices.restfullwebServices.security;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception{
		// All requests should be authenticated
		http.authorizeHttpRequests(
				auth ->auth.anyRequest().authenticated()
				);
		// if not authenticated a web page has to be shown
		http.httpBasic(withDefaults());
		
		//block csrf
		http.csrf().disable();
		return http.build();
		
	}
	

}
