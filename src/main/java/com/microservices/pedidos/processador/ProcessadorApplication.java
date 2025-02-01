package com.microservices.pedidos.processador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.microservices.pedidos.processador.entity")
public class ProcessadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessadorApplication.class, args);
	}

}
