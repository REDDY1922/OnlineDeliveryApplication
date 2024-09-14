package com.example.OnlineDeliveryApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineDeliveryApplication.enums.Status;
import com.example.OnlineDeliveryApplication.models.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{

	 // Fetch deliveries by DeliveryPersonnel (using personnelId)
    @Query("SELECT d FROM Delivery d WHERE d.deliveryPersonnel.id = :personnelId")
    List<Delivery> findByDeliveryPersonnel(@Param("personnelId") int personnelId);

    // Fetch deliveries by Status
    @Query("SELECT d FROM Delivery d WHERE d.status = :status")
    List<Delivery> findByStatus(@Param("status") Status status);
}
