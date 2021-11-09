package com.verusys.gourav.service;

import java.util.Optional;

import com.verusys.gourav.entity.User;

public interface IUserService {

	Long saveUser(User user);
	Optional<User> findByUsername(String username);
	void updateUserPwd(String pwd,Long userId);
}
