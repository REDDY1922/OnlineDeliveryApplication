package com.example.OnlineDeliveryApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.Exception.InvalidIdException;
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

	public Vendor getById(int vid) throws InvalidIdException{
		Optional<Vendor> optional=vendorRepository.findById(vid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Vendor Id Invalid");
		}
		return optional.get();
	}

	public Vendor getOne(int vid) throws InvalidIdException{
		Optional<Vendor> optional=vendorRepository.findById(vid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Invalid Vendor Id");
		}
		return optional.get();
	}

	public List<Vendor> getAllVendor() {
		// TODO Auto-generated method stub
		return vendorRepository.findAll();
	}

	public void deletevendor(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorRepository.delete(vendor);
	}


	public Vendor postVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		return vendorRepository.save(vendor);
	}

	public Vendor getvendorByUserId(int userId) throws InvalidIdException{
		Optional<Vendor> optional=vendorRepository.getVendorByUserId(userId);
		if(!optional.isPresent()) {
			throw new InvalidIdException("vid invalid");
		}
		return optional.get();
	}

}
