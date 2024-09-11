package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
