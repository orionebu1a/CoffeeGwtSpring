package com.voongc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.test")
public class CoffeeMachineApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoffeeMachineApplication.class, args);
	}
}
