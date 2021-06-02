package com.bridgelabz.insurancesystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "insurance_create")
public @Data class InsuranceCreateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ElementCollection
	@CollectionTable(name = "insurance_token",joinColumns = @JoinColumn(name = "id"))
	@Column(name = "user_id")
	private List<Long> userId;
	
	@ElementCollection
	@CollectionTable(name = "insurance_entity",joinColumns = @JoinColumn(name = "id"))
	@Column(name = "insurance_id")
	private List<Long> insuranceId;
	
	private int monthPeriod;
	private String status;
	private LocalDateTime registeredDate = LocalDateTime.now();
	private LocalDateTime updatedDate;
	
	public InsuranceCreateEntity() {}
	
}
