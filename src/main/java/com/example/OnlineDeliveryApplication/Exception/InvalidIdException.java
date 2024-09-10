package com.example.OnlineDeliveryApplication.Exception;

public class InvalidIdException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 814865839534748195L;
	
	private String message;

	public InvalidIdException(String message) {
		super();
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	
}
