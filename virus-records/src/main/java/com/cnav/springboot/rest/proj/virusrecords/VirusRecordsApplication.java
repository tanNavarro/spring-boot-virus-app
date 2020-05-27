package com.cnav.springboot.rest.proj.virusrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VirusRecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirusRecordsApplication.class, args);
	}

}
