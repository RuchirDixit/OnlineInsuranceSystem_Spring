package com.bridgelabz.insurancesystem.service;

import java.util.List;

import com.bridgelabz.insurancesystem.dto.InsuranceCreateDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;
import com.bridgelabz.insurancesystem.util.InsuranceResponse;
import com.bridgelabz.insurancesystem.util.Response;

public interface IInsuranceCreateService {

	// To add insurance data
	Response addInsuranceData(InsuranceCreateDTO insuranceDTO);

	// To get insurance data
	List<InsuranceResponse> getData(String token);

	
	// To get insurance data by status
	List<InsuranceResponse> getDataByStatus(String token, String status);

	// To get insurance data by month
	List<InsuranceResponse> getDataByMonth(String token, int month);

	// To update insurance data
	Response updateInsuranceData(String token, InsuranceCreateDTO insuranceDTO);

	// To delete insurance data
	Response deleteInsuranceData(String token);

	// To get insurance data for user
	List<InsuranceCategoryEntity> getInsuranceDataForUser(String token);

	// To get insurance data by claim
	List<InsuranceResponse> getInsuranceDataByClaim(String token, boolean claim);

	// To update insurance data by claim
	Response updateInsuranceClaim(String token, InsuranceCreateDTO insuranceDTO, boolean claim);

}
