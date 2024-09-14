package com.example.OnlineDeliveryApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.enums.Status;
import com.example.OnlineDeliveryApplication.models.Delivery;
import com.example.OnlineDeliveryApplication.models.DeliveryPersonnel;
import com.example.OnlineDeliveryApplication.repositories.DeliveryPersonnelRepository;
import com.example.OnlineDeliveryApplication.repositories.DeliveryRepository;

@Service
public class DeliveryPersonnelService {
	@Autowired
	private DeliveryPersonnelRepository deliveryPersonnelRepository;
	@Autowired
	private DeliveryRepository deliveryRepository;

	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return deliveryPersonnelRepository.existsByEmail(email);
	}

	public List<Delivery> getAllDeliveries() {
		// TODO Auto-generated method stub
		return deliveryRepository.findAll();
	}
	  // Fetch deliveries by Delivery Personnel (using personnelId)
    public List<Delivery> getDeliveriesByPersonnel(int personnelId) {
        return deliveryRepository.findByDeliveryPersonnel(personnelId);
    }

    // Fetch deliveries by Status
    public List<Delivery> getDeliveriesByStatus(Status status) {
        return deliveryRepository.findByStatus(status);
    }

	public DeliveryPersonnel insert(DeliveryPersonnel deliveryPersonnel) {
		// TODO Auto-generated method stub
		return deliveryPersonnelRepository.save(deliveryPersonnel);
	}

	

		
	  
}
