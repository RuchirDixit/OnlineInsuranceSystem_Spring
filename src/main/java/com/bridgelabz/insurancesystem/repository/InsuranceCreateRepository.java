package com.bridgelabz.insurancesystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;

public interface InsuranceCreateRepository extends JpaRepository<InsuranceCreateEntity, Long> {

	/**
	 * To find status with value passed in parameter
	 * @param status : To search for
	 * @return: List<InsuranceCreateEntity>
	 */
	List<InsuranceCreateEntity> findByStatus(String status);
	
	/**
	 * To find monthPeriod with value passed in parameter
	 * @param monthPeriod : To search for
	 * @return: List<InsuranceCreateEntity>
	 */
	List<InsuranceCreateEntity> findByMonthPeriod(int monthPeriod);
	
	/**
	 * To find claim with value passed in parameter
	 * @param claim : To search for
	 * @return: List<InsuranceCreateEntity>
	 */
	List<InsuranceCreateEntity> findByClaimed(boolean claim);
}
