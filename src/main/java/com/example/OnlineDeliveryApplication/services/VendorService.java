package com.example.OnlineDeliveryApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.models.Vendor;
import com.example.OnlineDeliveryApplication.repositories.VendorRepository;

@Service
public class VendorService {
	@Autowired
	private VendorRepository vendorRepository;

	public Vendor insert(Vendor vendor) {
		// TODO Auto-generated method stub
		return vendorRepository.save(vendor);
	}

	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return vendorRepository.findByEmail(email);
	}

}
