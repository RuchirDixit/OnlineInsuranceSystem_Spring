package com.bridgelabz.insurancesystem.service;

import java.util.List;

import com.bridgelabz.insurancesystem.dto.UserDTO;
import com.bridgelabz.insurancesystem.entity.UserEntity;
import com.bridgelabz.insurancesystem.util.Response;

public interface IUserService {

	Response addUser(UserDTO userDTO);

	Response updateUser(String token, UserDTO userDTO);

	List<UserEntity> getUsers(String token);

	Response deleteUser(String token);

}
