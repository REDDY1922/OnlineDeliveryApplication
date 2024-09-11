package com.example.OnlineDeliveryApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.repositories.VendorRepository;

@Service
public class VendorService {
	@Autowired
	private VendorRepository vendorRepository;

}
