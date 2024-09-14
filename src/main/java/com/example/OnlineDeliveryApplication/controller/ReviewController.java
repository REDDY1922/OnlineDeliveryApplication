package com.example.OnlineDeliveryApplication.controller;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.models.Customer;
import com.example.OnlineDeliveryApplication.models.Product;
import com.example.OnlineDeliveryApplication.models.Review;
import com.example.OnlineDeliveryApplication.services.CustomerService;
import com.example.OnlineDeliveryApplication.services.ProductService;
import com.example.OnlineDeliveryApplication.services.ReviewService;

@RestController
@RequestMapping("/Review")
public class ReviewController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/{cid}/{pid}")
	public ResponseEntity<?> addReview(@PathVariable("cid") int cid, @PathVariable("pid") int pid,
			@RequestBody Review review) throws InvalidIdException {

		Customer customer = customerService.getCustomer(cid);
		Product product = productService.getProductById(pid);

		review.setCustomer(customer);
		review.setProduct(product);
		reviewService.insert(review);
		return ResponseEntity.ok().body(review);
	}
	
	@GetMapping("/review/product/{id}")
	public ResponseEntity<?> getReviewsByProduct(@PathVariable int id){
		Optional<Product> product= productService.getOne(id);
		List<Review> list=reviewService.getByProductId(id);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/getall")
    public List<Review> getAllReviews(){
		return reviewService.getAll();
	}
	@GetMapping("/getall/{customerId}")
	public List<Review> getAllReviewsByCustomer(@PathVariable int customerId){
		return reviewService.getAllReviewsByCustomerId(customerId);
	}
	
}
