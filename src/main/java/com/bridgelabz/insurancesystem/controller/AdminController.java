package com.bridgelabz.insurancesystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.insurancesystem.dto.UserDTO;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.service.IAdminService;
import com.bridgelabz.insurancesystem.util.InsuranceResponse;
import com.bridgelabz.insurancesystem.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	/**
	 * To get all users from admin controller
	 * @param token : JWT data with userid
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getUsers/{token}")
	public ResponseEntity<List<?>> getUsers(@PathVariable String token){
		log.debug("Get all Users");
		List<UserEntity> response = adminService.getUsers(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all insurance data from admin controller
	 * @param token : JWT data with insuranceid
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getInsuranceData/{token}")
	public ResponseEntity<List<?>> getInsuranceData(@PathVariable String token){
		log.debug("Get all Insurance data");
		List<InsuranceCategoryEntity> response = adminService.getAllInsuranceData(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get entire data about users and insurance
	 * @param token : JWT token with id
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/get/{token}")
	public ResponseEntity<List<?>> getAllData(@PathVariable String token){
		log.debug("Get all InsuranceCreate: ");
		List<InsuranceResponse> response = adminService.getData(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all users with specific health condition from admin controller
	 * @param token : JWT data with userid
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getUserHealth/{token}")
	public ResponseEntity<List<?>> getUsersWithHealthCondition(@PathVariable String token,@RequestParam("health") String health){
		log.debug("Get all Users with health : " + health);
		List<UserEntity> response = adminService.getUsersWithHealthCondition(token,health);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all users with specific vehicle data from admin controller
	 * @param token : JWT data with userid
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getUserVehicle/{token}")
	public ResponseEntity<List<?>> getUsersWithVehicleData(@PathVariable String token,@RequestParam("vehicle") String vehicle){
		log.debug("Get all Users with vehicle data : " + vehicle);
		List<UserEntity> response = adminService.getUsersWithVehicleData(token,vehicle);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	/**
	 * To get all insurance data for particular category from admin controller
	 * @param token : JWT data with insuranceid
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getInsuranceCategory/{token}")
	public ResponseEntity<List<?>> getInsuranceForCategory(@PathVariable String token,@RequestParam("category") String category){
		log.debug("Get Insurance data for category: " + category);
		List<InsuranceCategoryEntity> response = adminService.getInsuranceForCategory(token,category);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
