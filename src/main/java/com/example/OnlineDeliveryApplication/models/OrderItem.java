package com.example.OnlineDeliveryApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int quantity;
	private long price;
	@ManyToOne
	private Orders order;
	@ManyToOne
	private Product product;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", price=" + price + ", order=" + order + ", product="
				+ product + "]";
	}
	
	
	
}
