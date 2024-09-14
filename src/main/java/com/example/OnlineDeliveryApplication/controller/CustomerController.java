package com.example.OnlineDeliveryApplication.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	 @GetMapping("/all")
		public List<Customer> getAllCustomers() { 
			List<Customer> list = customerService.getAllCustomers();
			return list;
		}
	 @GetMapping("/getByUserId/{userId}")
		public ResponseEntity<?> getCustomerByUserId(@PathVariable("userId") int userId) {
			try {
				Optional<Customer> customer = customerService.getCustomerByUserId(userId);
				if(customer.isPresent()){
					return ResponseEntity.ok().body(customer);
				}else{
					return null;
				}
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
	 @DeleteMapping("/delete/{id}") /* 8045/customer/delete/{id} */
		public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id) {

			try {
				// validate id
				Customer customer = customerService.getCustomer(id);
				// delete
				customerService.deleteCustomer(customer);
				return ResponseEntity.ok().body(" Account deleted successfully");
			} catch (InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}

		@PutMapping("/customer/update/{id}")
		public ResponseEntity<?> updateCustomer(@PathVariable("id") int id, @RequestBody Customer newCustomer) 
				throws InvalidIdException {
			Customer oldCustomer = customerService.getCustomer(id);
			if (newCustomer.getName() != null)
				oldCustomer.setName(newCustomer.getName());
			if (newCustomer.getEmail() != null)
				oldCustomer.setEmail(newCustomer.getEmail());
			if(newCustomer.getPhone()!=null)
				oldCustomer.setPhone(newCustomer.getPhone());
			oldCustomer = customerService.insert(oldCustomer);
			return ResponseEntity.ok().body(oldCustomer);
		}
		
		@GetMapping("/products/{vid}")
		public ResponseEntity<?> getCustomerByVendor(@PathVariable ("vid") int vid){
			List<Customer> customers = customerService.getCustomerByVendor(vid);
			return ResponseEntity.ok().body(customers);
		}

}
