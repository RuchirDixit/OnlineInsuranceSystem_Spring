package com.bridgelabz.insurancesystem.service;

import java.util.List;

import com.bridgelabz.insurancesystem.dto.UserDTO;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.util.Response;

public interface IUserService {

	// to add user
	Response addUser(UserDTO userDTO);

	// to update user
	Response updateUser(String token, UserDTO userDTO);

	// to get user
	List<UserEntity> getUsers(String token);

	// to delete user
	Response deleteUser(String token);

}
