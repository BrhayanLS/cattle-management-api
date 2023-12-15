package com.adgan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.adgan.service.config.SecurityConfig")
public class AdganApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdganApplication.class, args);
	}

}
