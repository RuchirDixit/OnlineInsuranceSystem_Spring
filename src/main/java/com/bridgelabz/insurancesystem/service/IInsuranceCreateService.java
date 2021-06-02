package com.bridgelabz.insurancesystem.service;

import java.util.List;

import com.bridgelabz.insurancesystem.dto.InsuranceCreateDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;
import com.bridgelabz.insurancesystem.util.InsuranceResponse;
import com.bridgelabz.insurancesystem.util.Response;

public interface IInsuranceCreateService {

	Response addInsuranceData(InsuranceCreateDTO insuranceDTO);

	List<InsuranceResponse> getData(String token);

}
