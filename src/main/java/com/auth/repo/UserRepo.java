package com.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auth.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	@Query("select u from User u where u.email = ?1")
	User getUserbyMail(String mail);

}
