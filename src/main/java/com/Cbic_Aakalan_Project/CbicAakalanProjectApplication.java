package com.Cbic_Aakalan_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CbicAakalanProjectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CbicAakalanProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CbicAakalanProjectApplication.class, args);
	}
}