package com.bridgelabz.insurancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OnlineInsuranceSystemApplication {

	public static void main(String[] args) {
		log.info("Starting OnlineInsuranceSystemApplication application!!");
		SpringApplication.run(OnlineInsuranceSystemApplication.class, args);
	}

}
