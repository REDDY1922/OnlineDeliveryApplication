package com.example.OnlineDeliveryApplication.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.enums.RoleType;
import com.example.OnlineDeliveryApplication.models.Admin;
import com.example.OnlineDeliveryApplication.models.User;
import com.example.OnlineDeliveryApplication.services.AdminService;
import com.example.OnlineDeliveryApplication.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Logger logger;
	@PostMapping("/add")
	public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
		// Check if the email already exists in the Admin table
	    String email = admin.getEmail();
	    if (adminService.existsByEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }
		User user=admin.getUser();
		String password=user.getPassword();
		 String encodedPassword = passwordEncoder.encode(password);
		    user.setPassword(encodedPassword);
		    user.setRole(RoleType.ADMIN);
		    user = userService.insert(user);
		    admin.setUser(user);
		    //insert admin into database
		    Admin insertAdmin=adminService.insert(admin);
		    logger.info("Admin signed up: {}", insertAdmin.getName());

		    // Return a success message along with the created Admin
		    return ResponseEntity.status(HttpStatus.OK).body(insertAdmin);
	}
}
