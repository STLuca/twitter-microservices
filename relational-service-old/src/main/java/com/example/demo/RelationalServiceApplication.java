package com.example.demo;

import com.example.demo.EventHandlers.EventHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class RelationalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalServiceApplication.class, args);
	}

	@Bean
	EventHandlers userEventHandler() {
		return new EventHandlers(restTemplate());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
