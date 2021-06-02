package com.bridgelabz.insurancesystem.dto;


import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;

public @Data class InsuranceDTO {

	@NotNull(message = "Insurance name cannot be null")
	private String insuranceName;
	
	@NotNull(message = "Insurance status cannot be null")
	public String insuranceStatus;
	
	@NotNull(message = "Insurance scheme cannot be null")
	public String insuranceScheme;

	@NotNull(message = "Insurance code cannot be null")
	public String insuranceCode;
}
