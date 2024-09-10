package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineDeliveryApplication.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
