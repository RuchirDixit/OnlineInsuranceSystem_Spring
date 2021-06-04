package com.bridgelabz.insurancesystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;

public interface InsuranceCategoryRepository extends JpaRepository<InsuranceCategoryEntity, Long> {
	/**
	 * Method to find insurance code containing value passed in parameter
	 * @param insuranceCode : To search
	 * @return: Optional<InsuranceCategoryEntity>
	 */
	Optional<InsuranceCategoryEntity> findByInsuranceCode(String insuranceCode);
}
