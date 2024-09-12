package com.example.OnlineDeliveryApplication.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
import com.example.OnlineDeliveryApplication.dto.NewproductDTO;
import com.example.OnlineDeliveryApplication.models.Category;
import com.example.OnlineDeliveryApplication.models.Product;
import com.example.OnlineDeliveryApplication.models.Vendor;
import com.example.OnlineDeliveryApplication.services.CategoryService;
import com.example.OnlineDeliveryApplication.services.ProductService;
import com.example.OnlineDeliveryApplication.services.VendorService;
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private VendorService vendorService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add/{vid}/{cid}")
	public ResponseEntity<?> addProduct(@PathVariable int vid,@PathVariable int cid,
			@RequestBody NewproductDTO productDTO) {
	Product product=new Product();
	
	try {
		Vendor vendor=vendorService.getById(vid);
		Category category=categoryService.getCategory(cid);
		product.setName(productDTO.getName());
		product.setProductDescription(productDTO.getProductDescription());
		product.setColour(productDTO.getColour());
		product.setSize(productDTO.getSize());
		product.setPrice(productDTO.getPrice());
		product.setStock(productDTO.getStock());
		product.setAvailability(productDTO.getAvailability());
		product.setVendor(vendor);
		product.setCategory(category);
		product=productService.insert(product);
		
		return ResponseEntity.ok().body(product);
	} catch (InvalidIdException e) {
		return ResponseEntity.ok().body(product);
	}	
	}
	@GetMapping("/category/all/{cid}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable int cid ,
			@RequestParam(required = false,defaultValue = "0") Integer page,
			@RequestParam(required = false,defaultValue = "1000000") Integer size) {
		/* validate category id. */
		try {
			Category category= categoryService.getCategory(cid);
			/* fetch products by category id with pagination */
			Pageable pageable=(Pageable) PageRequest.of(page, size);
			List<Product> list = productService.getProductsByCategoryId(cid,pageable);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("getall/{page}/{size}")
	public List<Product> getAllProducts(
			@RequestParam(required = false,defaultValue = "0") Integer page,
			@RequestParam(required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  (Pageable) PageRequest.of(page, size);
		return productService.getAllProducts(pageable);
	}

	@GetMapping("/product/getall")
    public List<Product> getAllProducts(){
		return productService.getAll();
	}
	@GetMapping("/vendor/vid")
	public ResponseEntity<?> getProductsBySeller(@PathVariable  int vid){
		try {
			Vendor vendor= vendorService.getOne(vid);
			List<Product> list=productService.getByvendorId(vid);
			return ResponseEntity.ok().body(list);
			
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/search/{qStr}")
	public List<Product> searchProductByName(@PathVariable String qStr) {
		List<Product> list= productService.searchProductByName(qStr);
		return list; 
	}
	 @DeleteMapping("/delete/{pid}")
	    public ResponseEntity<?> deleteProduct(@PathVariable("pid") int id) {
	        // Validate id
			
			try {
				Product product = productService.getProductById(id);
				// Delete the product
				productService.deleteProduct(product);
				return ResponseEntity.ok().body("product Deleted successfully");
			} catch (InvalidIdException e) {
				// TODO Auto-generated catch block
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
	    }
	 @DeleteMapping("/delete/{productId}/{vendorId}")
	 public ResponseEntity<?>deleteVendorProduct(@PathVariable int productId, @PathVariable int vendorId){
		 try {
			 productService.deleteProductByProductIdAndVendorID(productId,vendorId);
			 
		 }catch (Exception e){
			 return ResponseEntity.badRequest().body(e.getMessage());
			 
		 }
		 return ResponseEntity.ok().body("order deleted sucessfully");
	 }
	 
	 
}
