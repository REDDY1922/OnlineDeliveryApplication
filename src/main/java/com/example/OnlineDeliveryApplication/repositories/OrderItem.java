package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.OrdersItem;

public interface OrderItem extends JpaRepository<OrdersItem, Integer>{

}
