package com.example.OnlineDeliveryApplication.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineDeliveryApplication.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("SELECT p FROM Product p WHERE p.vendor.id = :vid")
	List<Product> getByVendorId(int vid);
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :qStr, '%'))")
    List<Product> searchProductByName(@Param("qStr") String query);
	
}
