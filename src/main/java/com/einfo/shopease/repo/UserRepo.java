package com.einfo.shopease.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfo.shopease.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String username);
	Optional<User> findByUserEmail(String email);

}
