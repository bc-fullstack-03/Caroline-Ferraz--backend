package com.parrot.parrotapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ParrotApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParrotApiApplication.class, args);
	}

}
