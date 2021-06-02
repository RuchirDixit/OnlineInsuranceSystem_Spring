package com.bridgelabz.insurancesystem.dto;

import java.io.File;

import javax.validation.constraints.NotBlank;

import lombok.Data;

public @Data class UserDTO {

	@NotBlank(message = "Full name cannot be blank")
	private String fullName;
	
	@NotBlank(message = "Permanent address cannot be blank")
	public String permanentAddress;
	
	@NotBlank(message = "Temporary address cannot be blank")
	public String temporaryAddress;
	
	private Long mobileNumber;
	
	private int age;
	
	@NotBlank(message = "Occupation cannot be blank")
	public String occupation;
	
	@NotBlank(message = "Family bg cannot be blank")
	public String familyBackground;
	public File kyc;
	
	@NotBlank(message = "Health condition cannot be blank")
	public String healthCondition;
	
	@NotBlank(message = "Vehicle Data cannot be blank")
	public String vehicleData;
}
