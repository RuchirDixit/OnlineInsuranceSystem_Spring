package com.bridgelabz.insurancesystem.service;

import com.bridgelabz.insurancesystem.dto.InsuranceCreateDTO;
import com.bridgelabz.insurancesystem.util.Response;

public interface IInsuranceCreateService {

	Response addInsuranceData(InsuranceCreateDTO insuranceDTO);

}
