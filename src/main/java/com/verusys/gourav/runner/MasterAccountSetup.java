package com.verusys.gourav.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.verusys.gourav.constant.UserRoles;
import com.verusys.gourav.entity.User;
import com.verusys.gourav.service.IUserService;
import com.verusys.gourav.util.MyMailUtil;
import com.verusys.gourav.util.UserUtil;

@Component
public class MasterAccountSetup implements CommandLineRunner {

	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;

	@Autowired
	private IUserService userservice;

	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailUtil mailUtil;

	@Override
	public void run(String... args) throws Exception {
		if (!userservice.findByUserName(username).isPresent()) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(displayName);
			user.setUserName(username);
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			Long genId = userservice.saveUser(user);
			if (genId != null)
				new Thread(new Runnable() {

					@Override
					public void run() {
						String text = "Your username is " + username + " and password is " + pwd;
						mailUtil.send(username, "Admin Added", text);
					}
				}).start();
		}
	}

}
