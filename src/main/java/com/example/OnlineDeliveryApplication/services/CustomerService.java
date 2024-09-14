package com.example.OnlineDeliveryApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.models.Customer;
import com.example.OnlineDeliveryApplication.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmail(email);
	}

	// Method to fetch customer details by ID
    public Customer getCustomer(int customerId) throws InvalidIdException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new InvalidIdException("Customer not found with ID: " + customerId));
    }

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerByUserId(int userId) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerByUserId(userId);
	}

	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		 customerRepository.delete(customer);
	}

	public Customer insert(Customer Customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(Customer);
	}

	public List<Customer> getCustomerByVendor(int vid) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerByVendor(vid);
	}
	



}
