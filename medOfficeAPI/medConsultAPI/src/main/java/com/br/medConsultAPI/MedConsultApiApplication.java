package com.br.medConsultAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedConsultApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedConsultApiApplication.class, args);
	}

}
