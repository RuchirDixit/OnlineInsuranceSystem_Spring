package com.bridgelabz.insurancesystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.exception.UserRegisterException;
import com.bridgelabz.insurancesystem.repository.InsuranceCategoryRepository;
import com.bridgelabz.insurancesystem.repository.InsuranceCreateRepository;
import com.bridgelabz.insurancesystem.repository.UserRepository;
import com.bridgelabz.insurancesystem.util.InsuranceResponse;
import com.bridgelabz.insurancesystem.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService implements IAdminService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InsuranceCategoryRepository insuranceCategoryRepository;
	
	@Autowired
	InsuranceCreateRepository insuranceCreateRepository;
	
	@Autowired
	TokenUtil tokenUtil;
	
	/**
	 * To get all user data for admin
	 * @param token: JWT with userid
	 * @return : List<UserEntity>
	 */
	@Override
	public List<UserEntity> getUsers(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isContactPresent = userRepository.findById(id);
		if(isContactPresent.isPresent()) {
			log.debug("Get all users");
			List<UserEntity> getUsers=userRepository.findAll();
			return getUsers;
		}
		else {
			log.error("Token not valid");
			throw new UserRegisterException(400,"Token not valid");
		}
	}

	/**
	 * To get insurance data for admin
	 * @param token: JWT with insuranceid
	 * @return : List<InsuranceCategoryEntity>
	 */
	@Override
	public List<InsuranceCategoryEntity> getAllInsuranceData(String token) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryEntity> isEntryPresent = insuranceCategoryRepository.findById(id);
		if(isEntryPresent.isPresent()) {
			log.debug("get");
			List<InsuranceCategoryEntity> getInsuranceData=insuranceCategoryRepository.findAll();
			return getInsuranceData;
		}
		else {
			log.error("Token not valid");
			throw new UserRegisterException(400,"Token not valid");
		}
	}

	/**
	 * To get insurance data along with user and new insurance data
	 * @param token : JWT with id
	 * @return : List<InsuranceResponse>
	 */
	@Override
	public List<InsuranceResponse> getData(String token) {
		Long userId=0L;
		Long insuranceId=0L;
		UserEntity userEntity=null;
		InsuranceCategoryEntity categoryEntity=null;
		
		long id = tokenUtil.decodeToken(token);
		System.out.println("ID : " + id);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		if(isDataPresent.isPresent()) {
			List<InsuranceCreateEntity> createList =  insuranceCreateRepository.findAll();		
			List<InsuranceResponse> insuranceCreateList = new ArrayList<>();
			for(InsuranceCreateEntity entity : createList) {
				List<Long> userIds = entity.getUserId();
				List<Long> insuranceIds = entity.getInsuranceId();
				for(Long uid : userIds) {
					userId=uid;
				}
				for(Long iid : insuranceIds) {
					insuranceId=iid;
				}
				Optional<UserEntity> isUserPresent = userRepository.findById(userId);
				if(isUserPresent.isPresent()) {
					userEntity = isUserPresent.get();
				}
				else {
					log.error("User not found.");
					throw new UserRegisterException(404,"User Not Found");
				}
				Optional<InsuranceCategoryEntity> isCategoryPresent = insuranceCategoryRepository.findById(insuranceId);
				if(isCategoryPresent.isPresent()) {
					categoryEntity = isCategoryPresent.get();
				}
				else {
					log.error("Category not found.");
					throw new UserRegisterException(404,"Category Not found");
				}
				insuranceCreateList.add(new InsuranceResponse(userEntity, categoryEntity, id,entity.getMonthPeriod(),entity.getStatus(),entity.getClaimed()));
			}
			return insuranceCreateList;
			
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"User Not found");
		}
	}

	/**
	 * To get users containing requested health condition
	 * @param token : JWT with userID, health : To search user with health condition
	 * @return : List<UserEntity>
	 */
	@Override
	public List<UserEntity> getUsersWithHealthCondition(String token, String health) {
		Long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isContactPresent = userRepository.findById(id);
		if(isContactPresent.isPresent()) {
			return userRepository.findByHealthCondition(health);
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"User Not found");
		}
	}

	/**
	 * To get users containing requested vehicle data
	 * @param token : JWT with userID, health : To search user with vehicle
	 * @return : List<UserEntity>
	 */
	@Override
	public List<UserEntity> getUsersWithVehicleData(String token, String vehicle) {
		Long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isContactPresent = userRepository.findById(id);
		if(isContactPresent.isPresent()) {
			return userRepository.findByVehicleData(vehicle);
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"User Not found");
		}
	}

	/**
	 * To get insurance data with requested insurance category
	 * @param token: JWT with id, category : Insurance name to search for
	 * @return : List<InsuranceCategoryEntity>
	 */
	@Override
	public List<InsuranceCategoryEntity> getInsuranceForCategory(String token, String category) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryEntity> isEntryPresent = insuranceCategoryRepository.findById(id);
		if(isEntryPresent.isPresent()) {
			log.debug("get");
			List<InsuranceCategoryEntity> getInsuranceData=insuranceCategoryRepository.findByInsuranceName(category);
			return getInsuranceData;
		}
		else {
			log.error("Token not valid");
			throw new UserRegisterException(400,"Token not valid");
		}
	}
}
