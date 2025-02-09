package com.city.management.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.city.management.service.infra.jpa")
@EntityScan("com.city.management.service.domain.entity")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
