package com.mdvns.mdvn.tag.papi;

import com.mdvns.mdvn.common.exception.RestDefautResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MdvnTagPapiApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@Bean
	public RestDefautResponse restDefautResponse() {
		return new RestDefautResponse();
	}


	public static void main(String[] args) {
		SpringApplication.run(MdvnTagPapiApplication.class, args);
	}
}
