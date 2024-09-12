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

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.enums.RoleType;
import com.example.OnlineDeliveryApplication.models.Address;
import com.example.OnlineDeliveryApplication.models.Customer;
import com.example.OnlineDeliveryApplication.models.User;
import com.example.OnlineDeliveryApplication.services.AddressService;
import com.example.OnlineDeliveryApplication.services.CustomerService;
import com.example.OnlineDeliveryApplication.services.UserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
    @Autowired 
    private AddressService addressService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Logger logger;
	@PostMapping("/customer/signup")
	public ResponseEntity<?> postCustomer(@RequestBody Customer customer) {
	    // Check if the email already exists in the Customer table
	    String email = customer.getEmail();
	    if (customerService.existsByEmail(email)) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
	    }

	    // If the email doesn't exist, proceed with the signup process
	    User user = customer.getUser();
	    String password = user.getPassword();
	    String encodedpassword = passwordEncoder.encode(password);
	    user.setPassword(encodedpassword);
	    user.setRole(RoleType.CUSTOMER);
	    Address address=addressService.postAddress(customer.getAddress());
	    customer.setAddress(address);
	    user = userService.insert(user);
	    customer.setUser(user);
	    int uid=customer.getCustomerId();
	    try {
			userService.sendEmailOnRegistration(uid);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return ResponseEntity.ok().body(customer);
	}
	
}
