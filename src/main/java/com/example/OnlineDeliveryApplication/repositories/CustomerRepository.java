package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	boolean findByEmail(String email);

}
