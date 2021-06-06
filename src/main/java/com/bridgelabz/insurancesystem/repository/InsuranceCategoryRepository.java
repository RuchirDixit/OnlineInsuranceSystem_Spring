package com.bridgelabz.insurancesystem.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;
import com.bridgelabz.insurancesystem.entity.UserEntity;

public interface InsuranceCategoryRepository extends JpaRepository<InsuranceCategoryEntity, Long> {
	/**
	 * Method to find insurance code containing value passed in parameter
	 * @param insuranceCode : To search
	 * @return: Optional<InsuranceCategoryEntity>
	 */
	Optional<InsuranceCategoryEntity> findByInsuranceCode(String insuranceCode);
	
	/**
	 * Method to find insurance name containing value passed in parameter
	 * @param insuranceName : To search
	 * @return: List<InsuranceCategoryEntity>
	 */
	List<InsuranceCategoryEntity> findByInsuranceName(String insuranceName);
	
	/**
	 * To find insurance between passed start and end dates
	 * @param localDateTime : From
	 * @param localDateTime2 : To
	 * @return : List<InsuranceCategoryEntity>
	 */
	List<InsuranceCategoryEntity> findByRegisteredDateBetween(LocalDateTime localDateTime,LocalDateTime localDateTime2);
}
