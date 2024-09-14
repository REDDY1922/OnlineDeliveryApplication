package com.example.OnlineDeliveryApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.models.Orders;
import com.example.OnlineDeliveryApplication.repositories.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	private OrdersRepository ordersRepository;

	 // Method to insert orders into the database
    public List<Orders> insert(List<Orders> ordersList) {
        // Persist the list of orders to the database and return the saved orders
        return ordersRepository.saveAll(ordersList);
    }

	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		ordersRepository.deleteById(id);
	}

	public List<Orders> getMyOrders(int cid) {
		// TODO Auto-generated method stub
		return ordersRepository.getMyOrders(cid);
	}
	

}
