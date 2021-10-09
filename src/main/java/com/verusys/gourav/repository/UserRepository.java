package com.verusys.gourav.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verusys.gourav.entity.User;
import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String username);
}
