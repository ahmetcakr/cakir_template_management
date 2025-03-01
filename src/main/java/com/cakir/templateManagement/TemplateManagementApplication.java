package com.cakir.templateManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("*")
@EnableCaching
public class TemplateManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateManagementApplication.class, args);
	}

}
