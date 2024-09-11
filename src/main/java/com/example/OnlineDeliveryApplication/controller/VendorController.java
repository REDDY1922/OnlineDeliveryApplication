package com.example.OnlineDeliveryApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.models.Product;
import com.example.OnlineDeliveryApplication.models.Vendor;
import com.example.OnlineDeliveryApplication.services.CategoryService;
import com.example.OnlineDeliveryApplication.services.ProductService;
import com.example.OnlineDeliveryApplication.services.VendorService;

@RestController
@RequestMapping("/product")
public class VendorController {
	@Autowired
	private VendorService vendorService;
	
}
