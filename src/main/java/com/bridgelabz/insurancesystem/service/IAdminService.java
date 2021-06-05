package com.bridgelabz.insurancesystem.service;

import java.util.List;

import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.util.InsuranceResponse;

public interface IAdminService {

	// To get all users
	List<UserEntity> getUsers(String token);

	// To get entire insurance data
	List<InsuranceCategoryEntity> getAllInsuranceData(String token);

	// To get entire data of users and their insurances
	List<InsuranceResponse> getData(String token);

	// To get user with specified health conditions
	List<UserEntity> getUsersWithHealthCondition(String token, String health);

	// To get user with specified vehicle data
	List<UserEntity> getUsersWithVehicleData(String token, String vehicle);

	// To get Insurance data with specified category
	List<InsuranceCategoryEntity> getInsuranceForCategory(String token, String category);

}
