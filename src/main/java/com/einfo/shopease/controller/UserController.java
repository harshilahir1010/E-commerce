package com.einfo.shopease.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.einfo.shopease.entity.User;
import com.einfo.shopease.repo.UserRepo;
import com.einfo.shopease.service.UserProService;

@RestController
public class UserController {
	
	@Autowired
	UserProService userProService;
	
	@Autowired
	UserRepo repo;
	
	@PostMapping("/register/new")
	//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SELLER','ROLE_USER')")
	public ResponseEntity<String> getRegister(@RequestBody User user){
		Optional<User> existUserName=repo.findByUserName(user.getUserName());
		Optional<User> existUserEmail=repo.findByUserEmail(user.getUserEmail());
		if(existUserName.isPresent()) {
			return ResponseEntity.badRequest().body("Username is already taken");
		}
		if(existUserEmail.isPresent()) {
			return ResponseEntity.badRequest().body("UserEmail is already taken");
		}
		
		userProService.createUser(user);
		return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
		
	}
	
	@GetMapping("/allUsers")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List getAllUsers() {
		return repo.findAll();
	}
	
}
