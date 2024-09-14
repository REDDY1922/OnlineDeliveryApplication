package com.example.OnlineDeliveryApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.OnlineDeliveryApplication.models.Vendor;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer>{

	boolean findByEmail(String email);

	 @Query(value = "SELECT v.* FROM Vendor v WHERE v.user_user_id = :userId", nativeQuery = true)
	    Optional<Vendor> getVendorByUserId(@Param("userId") int userId);

}
