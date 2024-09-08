package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.OrdersItem;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Integer>{

}
