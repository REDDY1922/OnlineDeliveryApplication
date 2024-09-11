package com.example.OnlineDeliveryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OnlineDeliveryApplication.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
