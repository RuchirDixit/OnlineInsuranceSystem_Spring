package com.bridgelabz.insurancesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.insurancesystem.dto.InsuranceCreateDTO;
import com.bridgelabz.insurancesystem.service.IInsuranceCreateService;
import com.bridgelabz.insurancesystem.util.Response;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/insurace_create")
@Slf4j
public class InsuraceCreateController {

	@Autowired
	IInsuranceCreateService insuranceCreateService;
	
	@PostMapping("/create")
	public ResponseEntity<Response> create(@RequestBody InsuranceCreateDTO insuranceDTO){
		log.debug("Create: " + insuranceDTO);
		Response response = insuranceCreateService.addInsuranceData(insuranceDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
