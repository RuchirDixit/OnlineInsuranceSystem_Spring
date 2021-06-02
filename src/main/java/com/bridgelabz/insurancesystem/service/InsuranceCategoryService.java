package com.bridgelabz.insurancesystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.insurancesystem.dto.InsuranceDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.exception.UserRegisterException;
import com.bridgelabz.insurancesystem.repository.InsuranceCategoryRepository;
import com.bridgelabz.insurancesystem.util.Response;
import com.bridgelabz.insurancesystem.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InsuranceCategoryService implements IInsuranceCategoryService {

	@Autowired
	private InsuranceCategoryRepository insuranceCategoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Override
	public Response addInsuranceData(InsuranceDTO insuranceDTO) {
		Optional<InsuranceCategoryEntity> isPresent = insuranceCategoryRepository.findByInsuranceCode(insuranceDTO.getInsuranceCode());
		if(isPresent.isPresent()) {
			log.error("Exists already.");
			throw new UserRegisterException(400,"Already exists");
		}else {
			InsuranceCategoryEntity insuranceEntity = modelMapper.map(insuranceDTO,InsuranceCategoryEntity.class);
			insuranceCategoryRepository.save(insuranceEntity);
			String token = tokenUtil.createToken(insuranceEntity.getId());
			log.debug("Added.");
			return new Response(200, "Insurance added successfully", token);
		}
	}

	@Override
	public Response updateInsuranceData(String token, InsuranceDTO insuranceDTO) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryEntity> isEntryPresent = insuranceCategoryRepository.findById(id);
		if(isEntryPresent.isPresent()) {
			isEntryPresent.get().setInsuranceCode(insuranceDTO.getInsuranceCode());
			isEntryPresent.get().setInsuranceName(insuranceDTO.getInsuranceName());
			isEntryPresent.get().setInsuranceScheme(insuranceDTO.getInsuranceScheme());
			isEntryPresent.get().setInsuranceStatus(insuranceDTO.getInsuranceStatus());
			isEntryPresent.get().setUpdatedDate(LocalDateTime.now());
			insuranceCategoryRepository.save(isEntryPresent.get());
			log.debug("Updated" + isEntryPresent.get());
			return new Response(200, "Updated successfully", null);
		}
		else {
			log.error("Not found.");
			throw new UserRegisterException(404,"Not found");
		}
	}

	@Override
	public List<InsuranceCategoryEntity> getInsuranceData(String token) {
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

	@Override
	public Response deleteInsuranceData(String token) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryEntity> isEntryPresent = insuranceCategoryRepository.findById(id);
		if(isEntryPresent.isPresent()) {
			log.debug("delete");
			insuranceCategoryRepository.delete(isEntryPresent.get());
			return new Response(200, "Deleted successfully", null);
		}
		else {
			log.error("Token not valid");
			throw new UserRegisterException(400,"Token not valid");
		}
	}

}
