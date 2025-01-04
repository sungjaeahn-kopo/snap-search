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

	@Bean(name = "webClientForDefault")
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl(baseUrl).defaultHeader("x-rapidapi-key", apiKey).build();
	}

	/**
	 * API 응답 데이터가 많을 경우, 사용하는 webClient
	 * @param builder
	 * @return
	 */
	@Bean(name = "webClientForBigData")
	public WebClient webClientForBigData(WebClient.Builder builder) {
		return builder
			.baseUrl(baseUrl)
			.defaultHeader("x-rapidapi-key", apiKey)
			.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(5 * 1024 * 1024)) // 버퍼 크기 설정
			.build();
	}
}
