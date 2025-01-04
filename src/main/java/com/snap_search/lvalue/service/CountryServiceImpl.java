package com.snap_search.lvalue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.snap_search.lvalue.api.ApiCountry;
import com.snap_search.lvalue.api.ApiResponse;
import com.snap_search.lvalue.model.Country;
import com.snap_search.lvalue.repository.CountryRepository;

import reactor.core.publisher.Mono;

@Service
public class CountryServiceImpl implements CountryService {

	private final WebClient webClient;
	private final CountryRepository countryRepository;

	public CountryServiceImpl(@Qualifier("webClientForDefault") WebClient webClient,
		CountryRepository countryRepository) {
		this.webClient = webClient;
		this.countryRepository = countryRepository;
	}

	@Override
	public List<Country> fetchAndSaveCountries() {
		// API 호출 및 데이터 저장 로직
		Mono<ApiResponse<ApiCountry>> responseMono = webClient.get()
			.uri("/countries")
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<ApiResponse<ApiCountry>>() {
			});

		ApiResponse<ApiCountry> response = responseMono.block();
		System.out.println("Country API : " + response);

		if (response != null && response.getResponse() != null) {
			List<Country> countries = response.getResponse().stream()
				.map(apiCountry -> {
					Country exisitingCountry = countryRepository.findByCodeAndName(apiCountry.getCode(),
						apiCountry.getName());

					if (exisitingCountry != null) {
						exisitingCountry.setFlag(apiCountry.getFlag());
						return exisitingCountry;
					} else {
						return new Country(
							apiCountry.getCode(),
							apiCountry.getName(),
							apiCountry.getFlag()
						);
					}
				})
				.collect(Collectors.toList());
			return countryRepository.saveAll(countries);
		}
		return List.of();
	}
}
