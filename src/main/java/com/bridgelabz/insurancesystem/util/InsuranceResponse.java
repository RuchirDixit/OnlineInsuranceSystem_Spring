package com.bridgelabz.insurancesystem.util;

import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import lombok.Data;
@Data
public class InsuranceResponse {

	UserEntity userEntity;
	InsuranceCategoryEntity categoryEntity;
	long id;
	
	public InsuranceResponse(UserEntity userEntity, InsuranceCategoryEntity categoryEntity, long id) {
		this.userEntity = userEntity;
		this.categoryEntity=categoryEntity;
		this.id=id;
	}

	
}
