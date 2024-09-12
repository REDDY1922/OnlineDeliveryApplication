package com.example.OnlineDeliveryApplication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.models.Customer;
import com.example.OnlineDeliveryApplication.models.User;
import com.example.OnlineDeliveryApplication.repositories.CustomerRepository;
import com.example.OnlineDeliveryApplication.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private JavaMailSender mailSender;


	public User loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	public User insert(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public void sendEmailOnRegistration(int userId) throws InvalidIdException {
	    Optional<Customer> optional = customerRepository.findById(userId);
	    
	    if (!optional.isPresent()) {
	        throw new InvalidIdException("id not found");
	    }
	    
	    Customer customer = optional.get();
	    // Assuming userRepository.findById method requires a parameter, replace it accordingly
	    // User user = userRepository.findById(customer.getId()).orElse(new User());
	    
	    String subject = "Registration confirmation";
	    String text = "Dear " + customer.getEmail() + ",\n\n" +
	            "Welcome to EPIC PICS -Have a great experience with our platform .\n\n" +
	            "Explore the best collections and great deels .\n\n" +
	            "Thank you for choosing us. Daily deals with exciting offers!\n\n" +
	            "Warm regards";
	           
 
	    // Assuming mailSender is an instance of JavaMailSender
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(customer.getEmail());
	    message.setSubject(subject);
	    message.setText(text);
	    mailSender.send(message);
	}

}
