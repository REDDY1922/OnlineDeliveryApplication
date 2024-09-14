package com.example.OnlineDeliveryApplication.services;

import java.awt.print.Pageable;
import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.models.Product;
import com.example.OnlineDeliveryApplication.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product insert(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	

	public List<Product> getAllProducts(Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public List<Product> getByvendorId(int vid) {
		// TODO Auto-generated method stub
		return productRepository.getByVendorId(vid);
	}

	public List<Product> searchProductByName(String qStr) {
		// TODO Auto-generated method stub
		return productRepository.searchProductByName(qStr);
	}

	public Product getProductById(int id) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Product> optional=productRepository.findById(id);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Product Id Invalid");
		}
		return optional.get();
	}

	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.delete(product);
	}

	

	


}
