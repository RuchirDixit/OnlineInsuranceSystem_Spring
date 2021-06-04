package com.bridgelabz.insurancesystem.service;

import java.util.List;

import com.bridgelabz.insurancesystem.dto.InsuranceDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.util.Response;

public interface IInsuranceCategoryService {

	// To add insurance data
	Response addInsuranceData(InsuranceDTO insuranceDTO);

	// To uodate insurance data
	Response updateInsuranceData(String token, InsuranceDTO insuranceDTO);

	// To get insurance data
	List<InsuranceCategoryEntity> getInsuranceData(String token);

	// To delete insurance data
	Response deleteInsuranceData(String token);

}
