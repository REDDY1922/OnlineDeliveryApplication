package com.example.OnlineDeliveryApplication.models;

import com.example.OnlineDeliveryApplication.enums.Availability;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(length = 2000)
	private String productDescription;
	private String colour;
	private int size;
	private long price;
	private int stock;
	@Enumerated(EnumType.STRING)
	private Availability availability;
	@ManyToOne
	private Vendor vendor;
	@ManyToOne
	private Category category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Availability getAvailability() {
		return availability;
	}
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productDescription=" + productDescription + ", colour="
				+ colour + ", size=" + size + ", price=" + price + ", stock=" + stock + ", availability=" + availability
				+ ", vendor=" + vendor + ", category=" + category + "]";
	}
	
	
	
	
}
