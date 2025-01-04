package com.snap_search.lvalue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.snap_search.lvalue.api.ApiResponse;
import com.snap_search.lvalue.model.Country;
import com.snap_search.lvalue.repository.CountryRepository;

import reactor.core.publisher.Mono;

@Service
public class CountryServiceImpl implements CountryService {

	private final WebClient webClient;
	private final CountryRepository countryRepository;

	public CountryServiceImpl(WebClient webClient, CountryRepository countryRepository) {
		this.webClient = webClient;
		this.countryRepository = countryRepository;
	}

	@Override
	public List<Country> fetchAndSaveCountries() {
		// API 호출 및 데이터 저장 로직
		Mono<ApiResponse> responseMono = webClient.get()
			.uri("/countries")
			.retrieve()
			.bodyToMono(ApiResponse.class);

		ApiResponse response = responseMono.block();
		if (response != null && response.getResponse() != null) {
			List<Country> countries = response.getResponse().stream()
				.map(apiCountry -> new Country(
					apiCountry.getCode(),
					apiCountry.getName(),
					apiCountry.getFlag()
				))
				.collect(Collectors.toList());
			return countryRepository.saveAll(countries);
		}
		return List.of();
	}
}
