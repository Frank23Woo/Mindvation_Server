package com.mdvns.mdvn.project.papi;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MdvnProjectPapiApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@Bean
	public RestDefaultResponse restDefaultResponse() {
		return new RestDefaultResponse();
	}
	public static void main(String[] args) {
		SpringApplication.run(MdvnProjectPapiApplication.class, args);
	}
}
