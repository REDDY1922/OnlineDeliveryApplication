package com.example.OnlineDeliveryApplication.controller;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.enums.RoleType;
import com.example.OnlineDeliveryApplication.enums.Status;
import com.example.OnlineDeliveryApplication.models.Delivery;
import com.example.OnlineDeliveryApplication.models.DeliveryPersonnel;
import com.example.OnlineDeliveryApplication.models.User;
import com.example.OnlineDeliveryApplication.services.DeliveryPersonnelService;
import com.example.OnlineDeliveryApplication.services.UserService;

import jakarta.persistence.criteria.Fetch;

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
	
	
	
	@PostMapping("/signup")
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

	    

	    // Insert the delivery_personnel into the database
	    DeliveryPersonnel insertedDeliveryPersonnel = deliveryPersonnelService.insert(deliveryPersonnel);

	    logger.info("DeliveryPersonnel signed up: {}", insertedDeliveryPersonnel.getName());

	    // Return a success message along with the created executive
	    return ResponseEntity.status(HttpStatus.OK).body(insertedDeliveryPersonnel);
	}
	// Fetch all deliveries
    @GetMapping("/deliveries/all")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> deliveries = deliveryPersonnelService.getAllDeliveries();
        return ResponseEntity.ok(deliveries);
    }

    // Fetch deliveries by DeliveryPersonnel
    @GetMapping("/{personnelId}/deliveries")
    public ResponseEntity<List<Delivery>> getDeliveriesByPersonnel(@PathVariable int personnelId) {
        List<Delivery> deliveries = deliveryPersonnelService.getDeliveriesByPersonnel(personnelId);
        return ResponseEntity.ok(deliveries);
    }

    // Fetch deliveries by Status
    @GetMapping("/deliveries/status")
    public ResponseEntity<List<Delivery>> getDeliveriesByStatus(@RequestParam Status status) {
        List<Delivery> deliveries = deliveryPersonnelService.getDeliveriesByStatus(status);
        return ResponseEntity.ok(deliveries);
    }

}
