package com.verusys.gourav.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verusys.gourav.entity.User;
import com.verusys.gourav.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	@GetMapping("/login")
	public String showLoginPage() {
		return "UserLogin";
	}

	@GetMapping("/setup")
	public String setup(HttpSession session, Principal p) {
		String userName = p.getName();
		User user = service.findByUserName(userName).get();
		session.setAttribute("userObj", user);
		return "UserHome";
	}
}
