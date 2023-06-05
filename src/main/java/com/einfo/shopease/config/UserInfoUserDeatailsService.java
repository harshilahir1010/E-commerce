package com.einfo.shopease.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.einfo.shopease.entity.User;
import com.einfo.shopease.repo.UserRepo;

@Component
public class UserInfoUserDeatailsService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userRepo.findByUserName(username);
		return user.map(UserInfoUserDetails::new)
		.orElseThrow(()->new UsernameNotFoundException("user not found "+username));
		
	}

}
