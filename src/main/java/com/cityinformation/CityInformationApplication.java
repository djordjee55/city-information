package com.cityinformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class CityInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityInformationApplication.class, args);
	}

}
