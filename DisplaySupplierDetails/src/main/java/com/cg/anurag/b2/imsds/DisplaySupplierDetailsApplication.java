package com.cg.anurag.b2.imsds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
@EnableEurekaClient
public class DisplaySupplierDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisplaySupplierDetailsApplication.class, args);
	}
@Bean
public RestTemplate getRestTemplate() {
	return new RestTemplate();
}
}
