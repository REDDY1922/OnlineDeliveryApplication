package com.example.OnlineDeliveryApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineDeliveryApplication.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	 @Query(value = "SELECT r.* FROM Review r WHERE r.product_product_id = :pid", nativeQuery = true)
	    List<Review> getByProductId(@Param("pid") int pid);

	@Query(value = "SELECT r.* FROM Review r WHERE r.customer_customer_id = :customerId", nativeQuery = true)
    List<Review> getAllReviewsByCustomerId(@Param("customerId") int customerId);
	
}
