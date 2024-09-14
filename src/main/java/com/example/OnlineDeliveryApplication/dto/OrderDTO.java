package com.example.OnlineDeliveryApplication.dto;

public class OrderDTO {

	private int id;
	private double totalAmount;
	private int quantity;
	private int cid;
	private int pid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", totalAmount=" + totalAmount + ", quantity=" + quantity + ", cid=" + cid
				+ ", pid=" + pid + "]";
	}
	
	
}
