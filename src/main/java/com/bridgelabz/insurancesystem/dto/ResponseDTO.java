package com.bridgelabz.insurancesystem.dto;

import lombok.Data;

public @Data class ResponseDTO {

	private String message;
	private Object data;
	
	public ResponseDTO(){
		
	}

	public ResponseDTO(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	
}
