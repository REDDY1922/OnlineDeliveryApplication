package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
