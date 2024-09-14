package com.example.OnlineDeliveryApplication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineDeliveryApplication.enums.Status;
import com.example.OnlineDeliveryApplication.models.Delivery;
import com.example.OnlineDeliveryApplication.models.DeliveryPersonnel;
import com.example.OnlineDeliveryApplication.models.Orders;
import com.example.OnlineDeliveryApplication.repositories.DeliveryPersonnelRepository;
import com.example.OnlineDeliveryApplication.repositories.DeliveryRepository;
import com.example.OnlineDeliveryApplication.repositories.OrdersRepository;

@Service
public class DeliveryService {

	 @Autowired
	    private DeliveryRepository deliveryRepository;

	    @Autowired
	    private DeliveryPersonnelRepository deliveryPersonnelRepository;

	    @Autowired
	    private OrdersRepository ordersRepository; // Assuming there's a repository for Orders

	    public Delivery assignOrderToDeliveryPersonnel(int orderId, int deliveryPersonnelId) throws Exception {
	        // Fetch the order
	        Optional<Orders> optionalOrder = ordersRepository.findById(orderId);
	        if (!optionalOrder.isPresent()) {
	            throw new Exception("Order not found with ID: " + orderId);
	        }

	        // Fetch the delivery personnel
	        Optional<DeliveryPersonnel> optionalPersonnel = deliveryPersonnelRepository.findById(deliveryPersonnelId);
	        if (!optionalPersonnel.isPresent()) {
	            throw new Exception("Delivery personnel not found with ID: " + deliveryPersonnelId);
	        }

	        // Create and save the delivery
	        Delivery delivery = new Delivery();
	        delivery.setOrder(optionalOrder.get());
	        delivery.setDeliveryPersonnel(optionalPersonnel.get());
	        delivery.setStatus(Status.PENDING); // Set an initial status
	        delivery.setDeliveryDate("2024-09-14");

	        return deliveryRepository.save(delivery);
	    }

}
