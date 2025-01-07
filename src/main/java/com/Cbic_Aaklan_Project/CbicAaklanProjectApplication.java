package com.Cbic_Aaklan_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CbicAaklanProjectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CbicAaklanProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CbicAaklanProjectApplication.class, args);
	}
}