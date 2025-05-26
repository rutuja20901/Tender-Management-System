package com.jwtauth.tendersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TenderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenderSystemApplication.class, args);
	}

}

/*
 * 1. delete is not working
 * 2. createby parameter is not working(current user is now shown at postman)
 * 
 */
