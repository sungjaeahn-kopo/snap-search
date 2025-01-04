package com.snap_search.lvalue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Value("${api.rapidapi.host}")
	private String baseUrl;

	@Value("${api.rapidapi.key}")
	private String apiKey;

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl(baseUrl).defaultHeader("x-rapidapi-key", apiKey).build();
	}
}
