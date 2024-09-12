package com.example.OnlineDeliveryApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.models.Address;
import com.example.OnlineDeliveryApplication.repositories.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public Address postAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

}
