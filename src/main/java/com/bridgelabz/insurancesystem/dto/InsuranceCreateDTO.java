package com.bridgelabz.insurancesystem.dto;

import java.util.List;

import lombok.Data;

public @Data class InsuranceCreateDTO {
	
	private List<Long> userID;
	private List<Long> insuranceID;
	private int monthPeriod;
	private String status;
}
