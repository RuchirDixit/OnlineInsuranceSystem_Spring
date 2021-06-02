package com.bridgelabz.insurancesystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.insurancesystem.dto.InsuranceCreateDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.repository.InsuranceCreateRepository;
import com.bridgelabz.insurancesystem.util.Response;
import com.bridgelabz.insurancesystem.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InsuranceCreateService implements IInsuranceCreateService{

	@Autowired
	InsuranceCreateRepository insuranceCreateRepository;
	
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

}
