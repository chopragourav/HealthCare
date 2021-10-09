package com.verusys.gourav.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.verusys.gourav.constant.UserRoles;
import com.verusys.gourav.entity.User;
import com.verusys.gourav.service.IUserService;
import com.verusys.gourav.util.UserUtil;

@Component
public class MasterAccountSetup implements CommandLineRunner {
	
	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String userName;
	
	@Autowired
	private IUserService userservice;
	
	@Autowired
	private UserUtil util;
	
	@Override
	public void run(String... args) throws Exception {
		if(!userservice.findByUserName(userName).isPresent()) {
			User user=new User();
			user.setDisplayName(displayName);
			user.setUserName(userName);
			user.setPassword(util.getPwd());
			user.setRole(UserRoles.ADMIN.name());
			userservice.saveUser(user);
		}
	}

}
