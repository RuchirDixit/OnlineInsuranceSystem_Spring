package com.bridgelabz.insurancesystem.service;

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
	
	Long userId=0L;
	Long insuranceId=0L;
	UserEntity userEntity=null;
	InsuranceCategoryEntity categoryEntity=null;
	
	/**
	 * To add insurance data
	 * @param insuranceDTO: To get data from InsuranceCreateDTO
	 * @return : Response
	 */
	@Override
	public Response addInsuranceData(InsuranceCreateDTO insuranceDTO) {
		InsuranceCreateEntity createEntity = modelMapper.map(insuranceDTO,InsuranceCreateEntity.class);
		insuranceCreateRepository.save(createEntity);
		String token = tokenUtil.createToken(createEntity.getId());
		log.debug("User added.");
		return new Response(200, "Registration successful", token);
	}

	
	/**
	 * To get insurance data
	 * @param token : JWT with id
	 * @return : List<InsuranceResponse>
	 */
	@Override
	public List<InsuranceResponse> getData(String token) {
		long id = tokenUtil.decodeToken(token);
		System.out.println("ID : " + id);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		if(isDataPresent.isPresent()) {
			List<InsuranceCreateEntity> createList =  insuranceCreateRepository.findAll();		
			return getInsuranceList(createList,id);
			
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"user Not found");
		}
		
	}

	/**
	 * To get insurance data by status
	 * @param token : JWT with id, status : String
	 * @return : List<InsuranceResponse>
	 */
	@Override
	public List<InsuranceResponse> getDataByStatus(String token, String status) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		if(isDataPresent.isPresent()) {
			List<InsuranceCreateEntity> statusList =  insuranceCreateRepository.findByStatus(status);			
			return getInsuranceList(statusList, id);
			
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"user Not found");
		}
	}

	/**
	 * To get insurance data by month
	 * @param token : JWT with id, month : int
	 * @return : List<InsuranceResponse>
	 */
	@Override
	public List<InsuranceResponse> getDataByMonth(String token, int month) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		if(isDataPresent.isPresent()) {
			List<InsuranceCreateEntity> monthsList =  insuranceCreateRepository.findByMonthPeriod(month);
			return getInsuranceList(monthsList, id);
			
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"user Not found");
		}
	}

	/**
	 * To update insurance data
	 * @param token : JWT with id, insuranceDTO: To get data from InsuranceCreateDTO
	 * @return : List<InsuranceResponse>
	 */
	@Override
	public Response updateInsuranceData(String token, InsuranceCreateDTO insuranceDTO) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		if(isDataPresent.isPresent()) {
			isDataPresent.get().setMonthPeriod(insuranceDTO.getMonthPeriod());
			isDataPresent.get().setStatus(insuranceDTO.getStatus());
			isDataPresent.get().setUserId(insuranceDTO.getUserID());
			isDataPresent.get().setInsuranceId(insuranceDTO.getInsuranceID());
			insuranceCreateRepository.save(isDataPresent.get());
			log.debug("Data updated" + isDataPresent.get());
			return new Response(200, "Data updated successfully", null);
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"user Not found");
		}
	}

	/**
	 * To delete insurance data
	 * @param token : JWT with id
	 * @return : Response
	 */
	@Override
	public Response deleteInsuranceData(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isPresent = insuranceCreateRepository.findById(id);
		if(isPresent.isPresent()) {
			insuranceCreateRepository.delete(isPresent.get());
			log.debug("Deleted!");
			return new Response(200, "Deleted successfully", null);
		}
		else {
			log.error("User not found");
			throw new UserRegisterException(404,"User not found");
		}
	}

	/**
	 * To get insurance data for user
	 * @param token : JWT with id
	 * @return : List<InsuranceCategoryEntity>
	 */
	@Override
	public List<InsuranceCategoryEntity> getInsuranceDataForUser(String token) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		Long insuranceId=0L;
		List<InsuranceCategoryEntity> insuranceList = new ArrayList<>();
		if(isDataPresent.isPresent()) {
			List<InsuranceCreateEntity> createList =  insuranceCreateRepository.findAll();
			log.info("Create Insurate response:" + createList);
			for(InsuranceCreateEntity element : createList) {
				List<Long> insuranceIds = element.getInsuranceId();
				for(Long iid : insuranceIds) {
					insuranceId=iid;
				}
				Optional<InsuranceCategoryEntity> insuranceCategory =  insuranceCategoryRepository.findById(insuranceId);
				if(insuranceCategory.isPresent()) {
					insuranceList.add(insuranceCategory.get());
				}
			}
			return insuranceList;
		}
		else {
			log.error("User not found");
			throw new UserRegisterException(404,"User not found");
		}
	}
	
	/**
	 * To get insurance data by claim
	 * @param token : JWT with id, claim : boolean
	 * @return : List<InsuranceResponse>
	 */
	@Override
	public List<InsuranceResponse> getInsuranceDataByClaim(String token, boolean claim) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		if(isDataPresent.isPresent()) {
			List<InsuranceCreateEntity> createEntity =insuranceCreateRepository.findByClaimed(claim);
			return getInsuranceList(createEntity, id);
		}
		else {
			log.error("User not found");
			throw new UserRegisterException(404,"User not found");
		}
	}
	
	/**
	 * To update insurance data by claim
	 * @param token : JWT with id, claim : boolean , insuranceDTO: To get data from InsuranceCreateDTO
	 * @return : Response
	 */
	@Override
	public Response updateInsuranceClaim(String token, InsuranceCreateDTO insuranceDTO, boolean claim) {
		long id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateEntity> isDataPresent = insuranceCreateRepository.findById(id);
		if(isDataPresent.isPresent()) {
			isDataPresent.get().setMonthPeriod(insuranceDTO.getMonthPeriod());
			isDataPresent.get().setStatus(insuranceDTO.getStatus());
			isDataPresent.get().setUserId(insuranceDTO.getUserID());
			isDataPresent.get().setInsuranceId(insuranceDTO.getInsuranceID());
			isDataPresent.get().setClaimed(claim);
			insuranceCreateRepository.save(isDataPresent.get());
			log.debug("Data updated" + isDataPresent.get());
			return new Response(200, "Data updated successfully", null);
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"user Not found");
		}
	}
	
	public List<InsuranceResponse> getInsuranceList(List<InsuranceCreateEntity> entityList,long id) {
		List<InsuranceResponse> insuranceCreateList = new ArrayList<>();
		for(InsuranceCreateEntity entity : entityList) {
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
			insuranceCreateList.add(new InsuranceResponse(userEntity, categoryEntity, id,entity.getMonthPeriod(),entity.getStatus(),entity.getClaimed()));
		}
		return insuranceCreateList;
	}
}
