package com.auth;

import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth.model.User;
import com.auth.service.IUserService;
import com.auth.service.UserService;

@SpringBootApplication
public class AuthAppApplication  {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AuthAppApplication.class, args);
		IUserService userService = ctx.getBean(UserService.class);

		Stream.of(new User("omar@gmail.com", "maftah"), new User("achraf@gmail.com", "aboul"))
				.forEach(user -> userService.addUser(user));
		
		userService.getAll().stream().forEach((user)->System.out.println(user.getEmail()));}
}
