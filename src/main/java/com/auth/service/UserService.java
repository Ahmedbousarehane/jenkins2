package com.auth.service;


import java.util.List;
 
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth.model.User;
import com.auth.repo.UserRepo;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	HttpSession httpSession;

	@Override
	public void addUser(User user) {
		userRepo.save(user);
	}

	@Override
	public User getLogin(String email) {
		return userRepo.getUserbyMail(email); 
	}

	@Override
	public boolean isCorrectLoginPassword(User user) {
		User u = getLogin(user.getEmail());
		if (!user.getMotDepasse().equals(u.getMotDepasse()))
			return false;
		httpSession.setAttribute("connectedUser", user);
		return true;
	}

	@Override
	public void logout() {
		httpSession.removeAttribute("connectedUser");
		}

	@Override
	public boolean isCorrectLogin(User user) {
 		return getLogin(user.getEmail()) == null ? false : true;
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}
}
