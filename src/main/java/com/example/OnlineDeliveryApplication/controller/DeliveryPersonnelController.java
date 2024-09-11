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
import com.example.OnlineDeliveryApplication.models.DeliveryPersonnel;
import com.example.OnlineDeliveryApplication.models.User;
import com.example.OnlineDeliveryApplication.services.DeliveryPersonnelService;
import com.example.OnlineDeliveryApplication.services.UserService;

@RestController
@RequestMapping("/DeliveryPersonnel")
public class DeliveryPersonnelController {
	@Autowired
	private DeliveryPersonnelService deliveryPersonnelService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Logger logger;
	
	
	@PostMapping("/executive/signup")
	public ResponseEntity<?> addDeliveryPersonnle(@RequestBody DeliveryPersonnel deliveryPersonnel) {
	    // Check if the email already exists in the deliveryPersonnel table
	    String email = deliveryPersonnel.getEmail();
	    if (deliveryPersonnelService.existsByEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }

	    User user = deliveryPersonnel.getUser();
	    String password = user.getPassword();
	    String encodedPassword = passwordEncoder.encode(password);
	    user.setPassword(encodedPassword);
	    user.setRole(RoleType.DELIVERY_PERSONNEL);
	    user = userService.insert(user);
	    deliveryPersonnel.setUser(user);

	    

	    // Insert the executive into the database
	    DeliveryPersonnel insertedDeliveryPersonnel = deliveryPersonnelService.insert(deliveryPersonnel);

	    logger.info("DeliveryPersonnel signed up: {}", insertedDeliveryPersonnel.getName());

	    // Return a success message along with the created executive
	    return ResponseEntity.status(HttpStatus.OK).body(insertedDeliveryPersonnel);
	}

}
