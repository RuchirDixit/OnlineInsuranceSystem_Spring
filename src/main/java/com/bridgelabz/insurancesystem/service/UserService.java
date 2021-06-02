package com.bridgelabz.insurancesystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.insurancesystem.dto.UserDTO;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.exception.UserRegisterException;
import com.bridgelabz.insurancesystem.repository.UserRepository;
import com.bridgelabz.insurancesystem.util.Response;
import com.bridgelabz.insurancesystem.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Override
	public Response addUser(UserDTO userDTO) {
		Optional<UserEntity> isPresent = userRepository.findByMobileNumber(userDTO.getMobileNumber());
		if(isPresent.isPresent()) {
			log.error("User exists already.");
			throw new UserRegisterException(400,"User already exists");
		}else {
			UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);
			userRepository.save(userEntity);
			String token = tokenUtil.createToken(userEntity.getId());
			log.debug("User added.");
			return new Response(200, "User registered successfully", token);
		}
	}

	@Override
	public Response updateUser(String token, UserDTO userDTO) {
		long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isUserPresent = userRepository.findById(id);
		if(isUserPresent.isPresent()) {
			isUserPresent.get().setFullName(userDTO.getFullName());
			isUserPresent.get().setFamilyBackground(userDTO.getFamilyBackground());
			isUserPresent.get().setPermanentAddress(userDTO.getPermanentAddress());
			isUserPresent.get().setTemporaryAddress(userDTO.getTemporaryAddress());
			isUserPresent.get().setAge(userDTO.getAge());
			isUserPresent.get().setHealthCondition(userDTO.getHealthCondition());
			isUserPresent.get().setKyc(userDTO.getKyc());
			isUserPresent.get().setOccupation(userDTO.getOccupation());
			isUserPresent.get().setVehicleData(userDTO.getVehicleData());
			isUserPresent.get().setMobileNumber(userDTO.getMobileNumber());
			isUserPresent.get().setUpdatedDate(LocalDateTime.now());
			userRepository.save(isUserPresent.get());
			log.debug("User updated" + isUserPresent.get());
			return new Response(200, "User updated successfully", null);
		}
		else {
			log.error("User not found.");
			throw new UserRegisterException(404,"user Not found");
		}
	}

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

	@Override
	public Response deleteUser(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<UserEntity> isUserPresent = userRepository.findById(id);
		if(isUserPresent.isPresent()) {
			userRepository.delete(isUserPresent.get());
			log.debug("Delete contact");
			return new Response(200, "User deleted successfully", null);
		}
		else {
			log.error("User not found");
			throw new UserRegisterException(404,"User not found");
		}
	}

}
