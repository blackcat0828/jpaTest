package com.example.alone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class AloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AloneApplication.class, args);
	}

}
