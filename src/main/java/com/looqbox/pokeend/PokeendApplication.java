package com.looqbox.pokeend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@EnableSwagger2WebMvc
@SpringBootApplication
public class PokeendApplication {

	//Utilizing WebClient as the base interface for the requests
	@Bean
	public WebClient webClient(WebClient.Builder builder) {

		// Here I defined the base url for the requests + the expected return type to always be present
		return builder
				.baseUrl("https://pokeapi.co/api/v2/pokemon/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(PokeendApplication.class, args);
	}

}
