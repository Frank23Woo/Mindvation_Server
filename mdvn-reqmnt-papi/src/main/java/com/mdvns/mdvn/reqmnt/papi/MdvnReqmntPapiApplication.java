package com.mdvns.mdvn.reqmnt.papi;

import com.mdvns.mdvn.common.beans.RestResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MdvnReqmntPapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdvnReqmntPapiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@Bean
	public RestResponse restResponse() {
		return new RestResponse();
	}
}
