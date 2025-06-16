package com.adgan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal de la aplicación AdGan.
 * Esta clase inicia la aplicación Spring Boot y configura el escaneo de componentes.
 * 
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
@SpringBootApplication
public class AdganApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdganApplication.class, args);
	}

}
