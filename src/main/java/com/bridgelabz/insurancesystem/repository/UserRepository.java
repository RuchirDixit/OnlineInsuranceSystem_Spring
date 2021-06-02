package com.bridgelabz.insurancesystem.repository;

import java.util.Optional;

import org.modelmapper.internal.bytebuddy.description.NamedElement.WithOptionalName;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.insurancesystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByMobileNumber(Long mobileNumber);
}
