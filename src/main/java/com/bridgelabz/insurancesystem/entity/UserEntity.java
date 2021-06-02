package com.bridgelabz.insurancesystem.entity;

import java.io.File;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "registereduser")
public @Data class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	
	private String fullName;
	public String permanentAddress;
	public String temporaryAddress;
	private Long mobileNumber;
	private int age;
	public String occupation;
	public String familyBackground;
	public File kyc;
	public String healthCondition;
	public String vehicleData;
	private LocalDateTime registeredDate = LocalDateTime.now();
	private LocalDateTime updatedDate;
	
	public UserEntity() {}
}
