package com.example.ai.image.analysis.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AiImageAnalysisLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiImageAnalysisLogApplication.class, args);
	}

}
