package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{

}
