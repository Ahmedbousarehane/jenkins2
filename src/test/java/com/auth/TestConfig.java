package com.auth;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth.model.User;
import com.auth.service.UserService;

@Configuration
public class TestConfig {

	@Bean
	UserService userService() {
		return new UserService();
	}

 
}
