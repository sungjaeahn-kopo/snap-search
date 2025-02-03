package com.snap_search.lvalue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.snap_search.lvalue.api.ApiLeague;
import com.snap_search.lvalue.api.ApiResponse;
import com.snap_search.lvalue.model.Country;
import com.snap_search.lvalue.model.League;
import com.snap_search.lvalue.repository.CountryRepository;
import com.snap_search.lvalue.repository.LeagueRepository;

import reactor.core.publisher.Mono;

@Service
public class LeagueServiceImpl implements LeagueService {

	private final WebClient webClient;
	private final CountryRepository countryRepository;
	private final LeagueRepository leagueRepository;

	public LeagueServiceImpl(@Qualifier("webClientForBigData") WebClient webClient, CountryRepository countryRepository,
		LeagueRepository leagueRepository) {
		this.webClient = webClient;
		this.countryRepository = countryRepository;
		this.leagueRepository = leagueRepository;
	}

	@Override
	public List<League> fetchAndSaveLeagues() {
		// API 호출 및 데이터 저장 로직
		Mono<ApiResponse<ApiLeague>> responseMono = webClient.get()
			.uri("/leagues")
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<ApiResponse<ApiLeague>>() {
			});

		ApiResponse<ApiLeague> response = responseMono.block();

		if (response != null && response.getResponse() != null) {
			return response.getResponse().stream().map(apiLeague -> {
					Country country = countryRepository.findByCodeAndName(
						apiLeague.getCountry().getCode(),
						apiLeague.getCountry().getName()
					);

					if (country == null) {
						country = new Country(
							apiLeague.getCountry().getCode(),
							apiLeague.getCountry().getName(),
							apiLeague.getCountry().getFlag()
						);
						countryRepository.save(country);
					}

					League exisitingLeague = leagueRepository.findById(apiLeague.getLeague().getId());

					if (exisitingLeague != null) {
						return exisitingLeague;
					} else {
						League newLeague = new League(
							apiLeague.getLeague().getId(),
							apiLeague.getLeague().getName(),
							apiLeague.getLeague().getType(),
							apiLeague.getLeague().getLogo(),
							country
						);

						return leagueRepository.save(newLeague);
					}
				})
				.collect(Collectors.toList());
		}
		return List.of();
	}
}
