package com.eventos.gestor_eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GestorEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorEventosApplication.class, args);
	}

}
