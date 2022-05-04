package com.example.fishing;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {ContextInstanceDataAutoConfiguration.class})
public class AppConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}