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
@Table(name = "insurance_data")
public @Data class InsuranceCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "insurance_id")
	private long id;
	
	private String insuranceName;
	public String insuranceStatus;
	public String insuranceScheme;
	public String insuranceCode;
	private LocalDateTime registeredDate = LocalDateTime.now();
	private LocalDateTime updatedDate;
	
	public InsuranceCategoryEntity() {}
}
