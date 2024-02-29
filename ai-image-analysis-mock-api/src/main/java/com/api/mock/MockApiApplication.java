package com.api.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MockApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockApiApplication.class, args);
	}

}
