package com.example.OnlineDeliveryApplication.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.models.Category;
import com.example.OnlineDeliveryApplication.services.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/add")//adding category
	public Category postCategory(@RequestBody Category category) {//method is mapped to url
		category =categoryService.postCategory(category);
		return category;
	}
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getCategory(@PathVariable int id) {
		try {
			Category category=categoryService.getCategory(id);
			return ResponseEntity.ok().body(category);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
				return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
		
	@GetMapping("/getall")
	public List<Category> getAll(@RequestParam(required = false,defaultValue = "0") Integer page,
			@RequestParam(required = false,defaultValue = "1000000") Integer size) {
		Pageable pageable = (Pageable) PageRequest.of(page, size);
	
	    return categoryService.getAll(pageable) ;
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
		Category category;
		try {
			category = categoryService.getCategory(id);
			categoryService.deleteCategory(category);
			return ResponseEntity.ok().body("deleted successfully");
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id,@RequestBody Category newCategory) {
		Category oldCategory;
		try {
			oldCategory = categoryService.getCategory(id);
			if(newCategory.getName()!=null) {
				oldCategory.setName(newCategory.getName());
			}
			if(newCategory.getDescription()!=null) {
				oldCategory.setDescription(newCategory.getDescription());
			}
			oldCategory=categoryService.postCategory(oldCategory);
			return ResponseEntity.ok().body(oldCategory);
			
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}