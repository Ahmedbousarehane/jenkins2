package com.auth.service;

import java.util.List;

import com.auth.model.User;

public interface IUserService {

	void addUser(User user);
	
	List<User> getAll();

	User getLogin(String email);

	boolean isCorrectLoginPassword(User user);
	
	boolean isCorrectLogin(User user);
	
	void logout();

}
