package com.einfo.shopease.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.einfo.shopease.entity.User;
import com.einfo.shopease.repo.UserRepo;
import com.einfo.shopease.service.UserProService;

@Service
public class UserProServiceImpl implements UserProService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public User createUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		return userRepo.save(user);
	}

}
