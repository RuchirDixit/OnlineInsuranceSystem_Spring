package com.bridgelabz.insurancesystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.insurancesystem.dto.InsuranceCreateDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;
import com.bridgelabz.insurancesystem.service.IInsuranceCreateService;
import com.bridgelabz.insurancesystem.util.InsuranceResponse;
import com.bridgelabz.insurancesystem.util.Response;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/newinsurance")
@Slf4j
public class InsuranceCreateController {

	@Autowired
	IInsuranceCreateService insuranceCreateService;
	
	/** To add new insurance
	 * To create Insurance create with userid and insuranceid
	 * @param insuranceDTO : To get data from InsuranceCreateDTO
	 * @return : ResponseEntity<Response>
	 */
	@PostMapping("/create")
	public ResponseEntity<Response> create(@RequestBody InsuranceCreateDTO insuranceDTO){
		log.debug("Create InsuranceCreate: " + insuranceDTO);
		Response response = insuranceCreateService.addInsuranceData(insuranceDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * To get entire data about users and insurance
	 * @param token : JWT token with id
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/get/{token}")
	public ResponseEntity<List<?>> getAllData(@PathVariable String token){
		log.debug("Get all InsuranceCreate: ");
		List<InsuranceResponse> response = insuranceCreateService.getData(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get new insurance data by status
	 * @param token : JWT token with id
	 * @param status : Status of insurance
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getByStatus/{token}")
	public ResponseEntity<List<?>> getDataByStatus(@PathVariable String token,@RequestParam("status") String status){
		log.debug("Get InsuranceCreate By Status");
		List<InsuranceResponse> response = insuranceCreateService.getDataByStatus(token,status);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get new insurance data by month period
	 * @param token : JWT token with id
	 * @param month : Month period of insurance
	 * @return  ResponseEntity<List<?>>
	 */
	@GetMapping("/getByMonth/{token}")
	public ResponseEntity<List<?>> getDataByMonthPeriod(@PathVariable String token,@RequestParam("month") int month){
		log.debug("Get InsuranceCreate By Month");
		List<InsuranceResponse> response = insuranceCreateService.getDataByMonth(token,month);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get new insurance data for user
	 * @param token : JWT token with id
	 * @return : ResponseEntity<?>
	 */
	@GetMapping("/getUserInsurance/{token}")
	public ResponseEntity<?> getInsuranceDataForUser(@PathVariable String token){
		log.debug("Get UserInsurance By Status");
		List<InsuranceCategoryEntity> response = insuranceCreateService.getInsuranceDataForUser(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get insurance data by claim
	 * @param token : JWT token with id
	 * @param claim : True/False
	 * @return : ResponseEntity<?>
	 */
	@GetMapping("/getInsuranceByClaim/{token}")
	public ResponseEntity<?> getInsuranceDataByClaim(@PathVariable String token,@RequestParam("claim") boolean claim){
		log.debug("Get InsuranceByClaim By Status");
		List<InsuranceResponse> response = insuranceCreateService.getInsuranceDataByClaim(token,claim);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To update new insurance data
	 * @param token : JWT token with id
	 * @param insuranceDTO : To get data from InsuranceCreateDTO
	 * @return: ResponseEntity<Response>
	 */
	@PutMapping("/update/{token}")
	public ResponseEntity<Response> updateInsurance(@PathVariable String token,@RequestBody InsuranceCreateDTO insuranceDTO){
		log.debug("Update Insurance: " + insuranceDTO);
		Response response = insuranceCreateService.updateInsuranceData(token,insuranceDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * To update new insurance claim status
	 * @param token : JWT token with id
	 * @param insuranceDTO : To get data from InsuranceCreateDTO
	 * @param claim : True/False
	 * @return : ResponseEntity<Response>
	 */
	@PutMapping("/updateClaim/{token}")
	public ResponseEntity<Response> updateInsuranceClaim(@PathVariable String token,@RequestBody InsuranceCreateDTO insuranceDTO,@RequestParam("claim") boolean claim){
		log.debug("Update Insurance Claim: " + insuranceDTO);
		Response response = insuranceCreateService.updateInsuranceClaim(token,insuranceDTO,claim);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * To delete new insurance data
	 * @param token : JWT token with id
	 * @return : ResponseEntity<Response>
	 */
	@DeleteMapping("/delete/{token}")
	public ResponseEntity<Response> deleteInsurance(@PathVariable String token){
		log.debug("Delete");
		Response response = insuranceCreateService.deleteInsuranceData(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
