package com.bridgelabz.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.insurancesystem.entity.InsuranceCreateEntity;

public interface InsuranceCreateRepository extends JpaRepository<InsuranceCreateEntity, Long> {

}
