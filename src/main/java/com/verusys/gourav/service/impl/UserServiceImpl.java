package com.verusys.gourav.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verusys.gourav.entity.User;
import com.verusys.gourav.repository.UserRepository;
import com.verusys.gourav.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository repo;
	@Override
	public Long saveUser(User user) {
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUserName(String username) {
		return repo.findByUserName(username);
	}

}
