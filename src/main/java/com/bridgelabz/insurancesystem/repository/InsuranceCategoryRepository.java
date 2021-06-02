package com.bridgelabz.insurancesystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.insurancesystem.entity.InsuranceCategoryEntity;

public interface InsuranceCategoryRepository extends JpaRepository<InsuranceCategoryEntity, Long> {
	Optional<InsuranceCategoryEntity> findByInsuranceCode(String insuranceCode);
}
