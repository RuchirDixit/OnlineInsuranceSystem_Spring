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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.insurancesystem.dto.InsuranceDTO;
import com.bridgelabz.insurancesystem.dto.UserDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.service.IInsuranceCategoryService;
import com.bridgelabz.insurancesystem.service.IUserService;
import com.bridgelabz.insurancesystem.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/insurance")
@Slf4j
public class InsuranceCategoryController {

	@Autowired
	private IInsuranceCategoryService insuranceService;
	
	/**
	 * To add insurance category
	 * @param insuranceDTO : DTO to enter insurance details
	 * @return : ResponseEntity<Response>
	 */
	@PostMapping("/create")
	public ResponseEntity<Response> createUser(@RequestBody InsuranceDTO insuranceDTO){
		log.debug("Create InsuranceCategory: " + insuranceDTO);
		Response response = insuranceService.addInsuranceData(insuranceDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * To update insurance category
	 * @param token : JWT with insurance id
	 * @param insuranceDTO : DTO to enter insurance details
	 * @return : ResponseEntity<Response>
	 */
	@PutMapping("/update/{token}")
	public ResponseEntity<Response> updateContact(@PathVariable String token,@RequestBody InsuranceDTO insuranceDTO){
		log.debug("Update InsuranceCategory: " + insuranceDTO);
		Response response = insuranceService.updateInsuranceData(token,insuranceDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all insurance category
	 * @param token : JWT with insurance id
	 * @return: ResponseEntity<List<?>>
	 */
	@GetMapping("/get/{token}")
	public ResponseEntity<List<?>> getAllContacts(@PathVariable String token){
		log.debug("Get InsuranceCategory");
		List<InsuranceCategoryEntity> response = insuranceService.getInsuranceData(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To delete insurance category
	 * @param token: JWT with insurance id
	 * @return: ResponseEntity<Response>
	 */
	@DeleteMapping("/delete/{token}")
	public ResponseEntity<Response> deleteContact(@PathVariable String token){
		log.debug("Delete InsuranceCategory");
		Response response = insuranceService.deleteInsuranceData(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
