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
public class MasterAccountSetupRunner implements CommandLineRunner {

	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
	@Autowired
	private MyMailUtil mailUtil ;
	
	public void run(String... args) throws Exception {
		if(!userService.findByUsername(username).isPresent()) {
			String pwd = userUtil.genPwd();
			User user = new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + username +", password is "+ pwd;
						mailUtil.send(username, "ADMIN ADDED", text);
					}
				}).start();
		}
	}	

}
