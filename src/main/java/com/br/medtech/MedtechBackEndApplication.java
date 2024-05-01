package com.br.medtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.br.medtech.model"})
public class MedtechBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedtechBackEndApplication.class, args);
	}

}
