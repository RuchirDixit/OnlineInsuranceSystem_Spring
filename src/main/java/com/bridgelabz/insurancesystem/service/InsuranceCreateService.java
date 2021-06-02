package com.bridgelabz.insurancesystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.insurancesystem.dto.InsuranceCreateDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.exception.UserRegisterException;
import com.bridgelabz.insurancesystem.repository.InsuranceCategoryRepository;
import com.bridgelabz.insurancesystem.repository.InsuranceCreateRepository;
import com.bridgelabz.insurancesystem.repository.UserRepository;
import com.bridgelabz.insurancesystem.util.InsuranceResponse;
import com.bridgelabz.insurancesystem.util.Response;
import com.bridgelabz.insurancesystem.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InsuranceCreateService implements IInsuranceCreateService{

	@Autowired
	InsuranceCreateRepository insuranceCreateRepository;
	
	@Autowired
	InsuranceCategoryRepository insuranceCategoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Response addInsuranceData(InsuranceCreateDTO insuranceDTO) {
		InsuranceCreateEntity createEntity = modelMapper.map(insuranceDTO,InsuranceCreateEntity.class);
		insuranceCreateRepository.save(createEntity);
		String token = tokenUtil.createToken(createEntity.getId());
		log.debug("User added.");
		return new Response(200, "Registration successful", token);
	}

	@Override
	public List<InsuranceResponse> getData(String token) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		Long userId=0L;
		Long insuranceId=0L;
		UserEntity userEntity=null;
		InsuranceCategoryEntity categoryEntity=null;
		List<InsuranceResponse> insuranceCreateList = new ArrayList<>();
		if(isDataPresent.isPresent()) {
			List<InsuranceCreateEntity> createList =  insuranceCreateRepository.findAll();
			for(InsuranceCreateEntity element : createList) {
				List<Long> userIds = element.getUserId();
				List<Long> insuranceIds = element.getInsuranceId();
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
					throw new UserRegisterException(404,"user Not found");
				}
				Optional<InsuranceCategoryEntity> isCategoryPresent = insuranceCategoryRepository.findById(insuranceId);
				if(isCategoryPresent.isPresent()) {
					categoryEntity = isCategoryPresent.get();
				}
				else {
					log.error("Category not found.");
					throw new UserRegisterException(404,"Category Not found");
				}
				insuranceCreateList.add(new InsuranceResponse(userEntity, categoryEntity, id));
			} // Outside FOR					
			return insuranceCreateList;
			
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"user Not found");
		}
		
	}

}
