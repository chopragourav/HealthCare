package com.verusys.gourav.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.verusys.gourav.entity.User;
import com.verusys.gourav.service.IUserService;
import com.verusys.gourav.util.MyMailUtil;
import com.verusys.gourav.util.UserUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	@Autowired
	private MyMailUtil mailUtil;

	@Autowired
	private UserUtil util;

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

	@GetMapping("/showPwdUpdate")
	public String showPwdUpdate() {
		return "UserPwdUpdate";
	}

	@PostMapping("/pwdUpdate")
	public String updatePwd(@RequestParam String password, HttpSession session, Model model) {
		// read current user from session
		User user = (User) session.getAttribute("userObj");
		// read userId
		Long userId = user.getId();

		// make service call
		service.updateUserPwd(password, userId);
		//updated password EMAIL 
		if (userId != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String text = "Your username is " + user.getUserName() + " and updated password is " + password;
					mailUtil.send(user.getUserName(), "Password Changed", text);
				}
			}).start();
		}
		model.addAttribute("message", "Password Updated!");
		return "UserPwdUpdate";
		// return "redirect:logout"
	}

	@GetMapping("/profile")
	public String showProfile() {
		return "UserProfile";
	}
}
