package com.example.OnlineDeliveryApplication.controller;

import javax.management.relation.Role;

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
import com.example.OnlineDeliveryApplication.models.Address;
import com.example.OnlineDeliveryApplication.models.User;
import com.example.OnlineDeliveryApplication.models.Vendor;
import com.example.OnlineDeliveryApplication.services.AddressService;
import com.example.OnlineDeliveryApplication.services.UserService;
import com.example.OnlineDeliveryApplication.services.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	@Autowired
	private VendorService vendorService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private Logger logger;
	

	@PostMapping("/signup")
	public ResponseEntity<?> addVendor(@RequestBody Vendor vendor) {
	    // Check if the email already exists in the Vendor table
	    String email = vendor.getEmail();
	    if (vendorService.existsByEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }
	    User user = vendor.getUser();
	    String password = user.getPassword();
	    String encodedPassword = passwordEncoder.encode(password);
	    user.setPassword(encodedPassword);
	    user.setRole(RoleType.VENDOR);
	    user = userService.insert(user);
	    vendor.setUser(user);

	    // Assuming you have an address associated with the vendor
	    Address address = addressService.postAddress(vendor.getAddress());
	    vendor.setAddress(address);

	    // Insert the vendor into the database
	    Vendor insertedVendor = vendorService.insert(vendor);
	    
	    logger.info("Seller signed up: {}", insertedVendor.getName());

	    // Return a success message along with the created vendor
	    return ResponseEntity.status(HttpStatus.OK).body(insertedVendor);
	}

}
