package com.verusys.gourav.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.verusys.gourav.entity.User;
import com.verusys.gourav.repository.UserRepository;
import com.verusys.gourav.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repo;

	@Override
	public Long saveUser(User user) {
		String pwd = user.getPassword();
		String accPwd = passwordEncoder.encode(pwd);
		user.setPassword(accPwd);
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUserName(String username) {
		return repo.findByUserName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> opt = findByUserName(username);
		if (!opt.isPresent())
			throw new UsernameNotFoundException(username);
		else {
			User user = opt.get();
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

		}
	}
}
