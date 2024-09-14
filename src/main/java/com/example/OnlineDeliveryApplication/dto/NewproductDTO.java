package com.example.OnlineDeliveryApplication.dto;

import com.example.OnlineDeliveryApplication.enums.Availability;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class NewproductDTO {
	 	private String colour;
	 	@Enumerated(EnumType.STRING)
	    private Availability availability;
	    private String name;
	    private int price;
	    private String productDescription;
	    private String size;
	    private String stock;
		public String getColour() {
			return colour;
		}
		public void setColour(String colour) {
			this.colour = colour;
		}
		public Availability getAvailability() {
			return availability;
		}
		public void setAvailability(Availability availability) {
			this.availability = availability;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getPrice() {
			return price;
		}
		
		public void setPrice(int price) {
			this.price = price;
		}
		public String getProductDescription() {
			return productDescription;
		}
		public void setProductDescription(String productDescription) {
			this.productDescription = productDescription;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getStock() {
			return stock;
		}
		public void setStock(String stock) {
			this.stock = stock;
		}
		@Override
		public String toString() {
			return "NewproductDTO [colour=" + colour + ", availability=" + availability + ", name=" + name + ", price="
					+ price + ", productDescription=" + productDescription + ", size=" + size + ", stock=" + stock
					+ "]";
		}
	    


}
