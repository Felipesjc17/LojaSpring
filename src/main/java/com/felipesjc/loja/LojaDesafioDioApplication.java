package com.felipesjc.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot Criado através da IDE STS.
 * Os seguintes módulos foram selecionados:
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 * 
 * @author Felipesjc17
 */

@EnableFeignClients
@SpringBootApplication
public class LojaDesafioDioApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaDesafioDioApplication.class, args);
	}
}