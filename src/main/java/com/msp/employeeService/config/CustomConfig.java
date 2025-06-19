package com.msp.employeeService.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
