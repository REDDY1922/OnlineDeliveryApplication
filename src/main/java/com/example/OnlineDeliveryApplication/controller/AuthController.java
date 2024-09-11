package com.example.OnlineDeliveryApplication.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.OnlineDeliveryApplication.models.User;
import com.example.OnlineDeliveryApplication.services.UserService;

public class AuthController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/login")
	public User login(Principal  principal) {  
		String username = principal.getName();
		System.out.println(principal);
		User user = (User)userService.loadUserByUsername(username);
		return  user;

}
}
