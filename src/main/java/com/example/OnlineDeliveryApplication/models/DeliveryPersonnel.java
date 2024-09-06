package com.example.OnlineDeliveryApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class DeliveryPersonnel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String phone;
	private String vehicleDetails;
	@OneToOne
	private User user;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVehicleDetails() {
		return vehicleDetails;
	}
	public void setVehicleDetails(String vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "DeliveryPersonnel [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", vehicleDetails=" + vehicleDetails + ", user=" + user + "]";
	}
	
	
	
}
