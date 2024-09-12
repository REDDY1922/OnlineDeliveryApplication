package com.example.OnlineDeliveryApplication.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.OnlineDeliveryApplication.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("select p from product p where p.category.id=?1")
	List<Product> getproductsByCategoryId(int cid, Pageable pageable);
	@Query("select p from product p where p.vendor.id=?1")
	List<Product> getByVendorId(int vid);
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	List<Product> searchProductByName(String qStr);
	 @Query("select p from Product p where p.seller.id =?1")
	int deleteProductByProductIdAndAndVendor_VendorId(int productId, int vendorId);

}
