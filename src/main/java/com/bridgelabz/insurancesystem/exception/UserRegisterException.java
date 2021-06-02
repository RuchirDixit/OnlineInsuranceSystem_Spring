package com.bridgelabz.insurancesystem.exception;

import java.util.Locale;

import org.springframework.web.bind.annotation.ResponseStatus;
import com.bridgelabz.insurancesystem.util.Response;
import com.bridgelabz.insurancesystem.util.ErrorResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ResponseStatus
@Data
@Slf4j
public class UserRegisterException extends RuntimeException {

	public UserRegisterException(int statusCode, String statusmessage) {
		super(statusmessage);
		StatusCode = statusCode;
		Statusmessage = statusmessage;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int StatusCode;
	private String Statusmessage;
	
	public Response getErrorResponse() {
		return getErrorResponse(Locale.getDefault());
	}
	public Response getErrorResponse(Locale locale) {
		log.error("Error msg:" + Statusmessage);
		ErrorResponse err = new ErrorResponse(StatusCode, Statusmessage, getStackTrace());
		err.setStatusCode(getStatusCode());
		err.setStatusmessage(getStatusmessage());
		return err;
	

}}
