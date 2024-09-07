package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
