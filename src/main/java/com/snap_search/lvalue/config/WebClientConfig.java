package com.snap_search.lvalue.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

	private static final Logger logger = LoggerFactory.getLogger(WebClientConfig.class);

	@Value("${api.rapidapi.host}")
	private String baseUrl;

	@Value("${api.rapidapi.key}")
	private String apiKey;

	@Bean(name = "webClientForDefault")
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl(baseUrl).defaultHeader("x-rapidapi-key", apiKey).filter(logRequest())
			.filter(logResponse()).build();
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

	// 요청 로깅 필터
	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(request -> {
			logger.info("Request: {} {}", request.method(), request.url());
			request.headers().forEach((name, values) ->
				values.forEach(value -> logger.info("Request Header: {}={}", name, value))
			);
			return Mono.just(request);
		});
	}

	// 응답 로깅 필터
	private ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(response -> {
			logger.info("Response status: {}", response.statusCode());
			response.headers().asHttpHeaders().forEach((name, values) ->
				values.forEach(value -> logger.info("Response Header: {}={}", name, value))
			);
			return Mono.just(response);
		});
	}
}
