package com.example.OnlineDeliveryApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.models.Delivery;
import com.example.OnlineDeliveryApplication.services.DeliveryService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/assign")
    public ResponseEntity<Delivery> assignOrderToDeliveryPersonnel(
        @RequestParam int orderId,
        @RequestParam int deliveryPersonnelId) {

        try {
            Delivery delivery = deliveryService.assignOrderToDeliveryPersonnel(orderId, deliveryPersonnelId);
            return ResponseEntity.ok(delivery);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}