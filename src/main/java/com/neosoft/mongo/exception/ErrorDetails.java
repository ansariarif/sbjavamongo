package com.neosoft.mongo.exception;

import java.util.Date;

public class ErrorDetails {
	
	private Date tinmestamp;
	private String message;
	private String details;
	
	public ErrorDetails(Date tinmestamp, String message, String details) {
		super();
		this.tinmestamp = tinmestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTinmestamp() {
		return tinmestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	
	
	
	
	

}
