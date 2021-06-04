package com.bridgelabz.insurancesystem.util;

import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import lombok.Data;
@Data
public class InsuranceResponse {

	UserEntity userEntity;
	InsuranceCategoryEntity categoryEntity;
	long id;
	private int monthPeriod;
	private String status;
	private boolean claimed;
	public InsuranceResponse(UserEntity userEntity, InsuranceCategoryEntity categoryEntity, long id, int monthPeriod,
			String status,boolean claimed) {
		super();
		this.userEntity = userEntity;
		this.categoryEntity = categoryEntity;
		this.id = id;
		this.monthPeriod = monthPeriod;
		this.status = status;
		this.claimed = claimed;
	}
	
	

	
}
