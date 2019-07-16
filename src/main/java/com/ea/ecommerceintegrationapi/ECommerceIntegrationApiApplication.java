package com.ea.ecommerceintegrationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ECommerceIntegrationApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(ECommerceIntegrationApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ECommerceIntegrationApiApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
