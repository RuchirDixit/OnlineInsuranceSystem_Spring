package com.bridgelabz.insurancesystem.service;

import java.util.List;

import com.bridgelabz.insurancesystem.dto.InsuranceDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.util.Response;

public interface IInsuranceCategoryService {

	Response addInsuranceData(InsuranceDTO insuranceDTO);

	Response updateInsuranceData(String token, InsuranceDTO insuranceDTO);

	List<InsuranceCategoryEntity> getInsuranceData(String token);

	Response deleteInsuranceData(String token);

}
