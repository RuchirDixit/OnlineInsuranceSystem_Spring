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

import com.bridgelabz.insurancesystem.dto.UserDTO;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.service.IUserService;
import com.bridgelabz.insurancesystem.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<Response> createUser(@RequestBody UserDTO userDTO){
		log.debug("Create User : " + userDTO);
		Response response = userService.addUser(userDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{token}")
	public ResponseEntity<Response> updateContact(@PathVariable String token,@RequestBody UserDTO userDTO){
		log.debug("Update contact : " + userDTO);
		Response response = userService.updateUser(token,userDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getUsers/{token}")
	public ResponseEntity<List<?>> getAllContacts(@PathVariable String token){
		log.debug("Get all contacts");
		List<UserEntity> response = userService.getUsers(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{token}")
	public ResponseEntity<Response> deleteContact(@PathVariable String token){
		log.debug("Delete contact");
		Response response = userService.deleteUser(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
