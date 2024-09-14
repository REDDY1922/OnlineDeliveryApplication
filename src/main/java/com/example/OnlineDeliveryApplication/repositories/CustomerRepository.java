package com.example.OnlineDeliveryApplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.OnlineDeliveryApplication.models.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	boolean findByEmail(String email);
	 @Query("SELECT c FROM Customer c WHERE c.id = :customerId")
	Customer getCustomer(int customerId);
	Optional<Customer> getCustomerByUserId(int userId);
	    @Query(value = "SELECT c.* FROM Customer c, Orders o, Product p " +
	                   "WHERE o.product_product_id = p.product_id " +
	                   "AND o.customer_customer_id = c.customer_id " +
	                   "AND p.vendor_vendor_id = :vendorId", nativeQuery = true)
	    List<Customer> getCustomerByVendor(@Param("vendorId") int vendorId);
	}
