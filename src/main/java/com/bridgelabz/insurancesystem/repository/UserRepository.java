package com.bridgelabz.insurancesystem.repository;

import java.util.List;
import java.util.Optional;

import org.modelmapper.internal.bytebuddy.description.NamedElement.WithOptionalName;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.insurancesystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	/**
	 * To find data by mobile number with value passed as parameter
	 * @param mobileNumber : To search for
	 * @return : Optional<UserEntity>
	 */
	Optional<UserEntity> findByMobileNumber(Long mobileNumber);
	
	/**
	 * To find user with health condition passed as parameter
	 * @param health : To search for
	 * @return : List<UserEntity>
	 */
	List<UserEntity> findByHealthCondition(String health);
	
	/**
	 * To find user with vehicle data passed as parameter
	 * @param vehicle : To search for
	 * @return : List<UserEntity>
	 */
	List<UserEntity> findByVehicleData(String vehicle);
}
