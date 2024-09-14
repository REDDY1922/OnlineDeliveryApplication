package com.example.OnlineDeliveryApplication.models;
import com.example.OnlineDeliveryApplication.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deliveryId;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	private String deliveryDate;
	@ManyToOne
	private Orders order;
	@ManyToOne
	private DeliveryPersonnel deliveryPersonnel;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public DeliveryPersonnel getDeliveryPersonnel() {
		return deliveryPersonnel;
	}
	public void setDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel) {
		this.deliveryPersonnel = deliveryPersonnel;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	@Override
	public String toString() {
		return "Delivery [deliveryId=" + deliveryId + ", status=" + status + ", deliveryDate=" + deliveryDate
				+ ", order=" + order + ", deliveryPersonnel=" + deliveryPersonnel + "]";
	}
	
}
