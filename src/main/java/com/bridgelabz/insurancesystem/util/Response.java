package com.bridgelabz.insurancesystem.util;

import lombok.Data;

@Data
public class Response {
	
	private Integer StatusCode;
	private String Statusmessage;
	private Object token;
	public Response(Integer statusCode, String statusmessage, Object token) {
		super();
		StatusCode = statusCode;
		Statusmessage = statusmessage;
		this.token = token;
	}
	

}
