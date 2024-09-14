package com.example.OnlineDeliveryApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.models.Review;
import com.example.OnlineDeliveryApplication.repositories.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	public void insert(Review review) {
		// TODO Auto-generated method stub
		reviewRepository.save(review);
	}

	public List<Review> getByProductId(int pid) {
		// TODO Auto-generated method stub
		return reviewRepository.getByProductId(pid);
	}

	public List<Review> getAll() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

	public List<Review> getAllReviewsByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllReviewsByCustomerId(customerId);
	}




	

	
}
