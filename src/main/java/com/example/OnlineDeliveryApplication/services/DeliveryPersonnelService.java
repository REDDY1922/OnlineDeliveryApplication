package com.example.OnlineDeliveryApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.models.DeliveryPersonnel;
import com.example.OnlineDeliveryApplication.repositories.DeliveryPersonnelRepository;

@Service
public class DeliveryPersonnelService {
	@Autowired
	private DeliveryPersonnelRepository deliveryPersonnelRepository;

	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return deliveryPersonnelRepository.existsByEmail(email);
	}

	public DeliveryPersonnel insert(DeliveryPersonnel deliveryPersonnel) {
		// TODO Auto-generated method stub
		return deliveryPersonnelRepository.save(deliveryPersonnel);
	}


}
