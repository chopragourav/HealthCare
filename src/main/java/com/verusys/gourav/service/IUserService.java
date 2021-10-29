package com.verusys.gourav.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.verusys.gourav.entity.User;

public interface IUserService {
	Long saveUser(User user);

	Optional<User> findByUserName(String username);

	void updateUserPwd(String password, Long userId);

}
