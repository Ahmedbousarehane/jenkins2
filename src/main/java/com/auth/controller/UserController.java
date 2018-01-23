package com.auth.controller;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.auth.model.User;
import com.auth.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/home")
	Callable<String> index(HttpSession httpSession) {
		return () -> {
		User user = (User) httpSession.getAttribute("connectedUser");
		if (user != null) {
			return "hello";
		}
		return "index";};
	}

	@PostMapping(value = "/login")
	Callable<String> login(@ModelAttribute User user, HttpSession httpSession, Model model)
			throws InterruptedException {
		return () -> {
			Thread.sleep(5000l);
			if (userService.isCorrectLoginPassword(user)) {
				return "hello";
			}
			model.addAttribute("error", "your password is incorrect");
			return "redirect:/home";
		};
	}

	@GetMapping(value = "/login")
	String getLogin() {
		return "redirect:/home ";
	}

	@GetMapping(value = "/logout")
	String logout(HttpSession httpSession) {
		userService.logout();
		return "index";
	}
	@GetMapping(value = "/achraf")
	String achraf(HttpSession httpSession) {
		
		return "achraf";
	}
	@GetMapping(value = "/omar")
	String omar(HttpSession httpSession) {
		
		return "omar";
	}

}
