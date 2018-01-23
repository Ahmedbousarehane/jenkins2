package com.auth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.WebApplicationInitializer;

import com.auth.service.UserService;

@Configuration
public class Config {

	@Bean
	UserService userService() {
		return new UserService();
	}
	
	@Primary
	@Bean
	WebApplicationInitializer CourtWebApplicationInitializer(){
		return new CourtWebApplicationInitializer();
	}


}
