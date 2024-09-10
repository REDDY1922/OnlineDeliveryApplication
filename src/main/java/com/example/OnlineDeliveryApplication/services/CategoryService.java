package com.example.OnlineDeliveryApplication.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.models.Category;
import com.example.OnlineDeliveryApplication.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Category postCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	public Category getCategory(int id) throws InvalidIdException{
		Optional<Category> optional=categoryRepository.findById(id);
		if(!optional.isPresent()) {
			throw new InvalidIdException("category Id invalid");
		}
		return optional.get();
	}

	public List<Category> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	public void deleteCategory(Category category){
		categoryRepository.delete(category);
		
		
	}

}
