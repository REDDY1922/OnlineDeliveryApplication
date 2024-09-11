package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.DeliveryPersonnel;

public interface DeliveryPersonnelRepository extends JpaRepository<DeliveryPersonnel, Integer>{

	boolean existsByEmail(String email);

	 

}
