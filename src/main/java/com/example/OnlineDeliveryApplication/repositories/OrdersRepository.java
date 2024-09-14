package com.example.OnlineDeliveryApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineDeliveryApplication.models.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	 @Query("SELECT o FROM Orders o WHERE o.customer.id = :cid")
	    List<Orders> getMyOrders(@Param("cid") int cid);
}
