package com.snap_search.lvalue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.snap_search.lvalue.dto.CountryLeagueMappingDTO;
import com.snap_search.lvalue.repository.CountryLeagueMappingRepository;

@Service
public class CountryLeagueMappingServiceImpl implements CountryLeagueMappingService {

	private final CountryLeagueMappingRepository countryLeagueRepository;

	public CountryLeagueMappingServiceImpl(CountryLeagueMappingRepository countryLeagueRepository) {
		this.countryLeagueRepository = countryLeagueRepository;
	}

	@Override
	public List<CountryLeagueMappingDTO> getCountiesBySeason(String season) {
		return countryLeagueRepository.findCountriesBySeason(season).stream()
			.map(entity -> new CountryLeagueMappingDTO(
				entity.getLeagueSeason(),
				entity.getCountryId(),       // Entity에서 Country 관련 데이터를 가져옵니다.
				entity.getCountryName(),
				entity.getCountryFlag()
			))
			.collect(Collectors.toList());
	}

	@Override
	public List<CountryLeagueMappingDTO> getLeaguesBySeasonAndCountry(String season, Long countryId) {
		return countryLeagueRepository.findLeaguesBySeasonAndCountry(season, countryId).stream()
			.map(entity -> new CountryLeagueMappingDTO(
				entity.getLeagueSeason(),
				entity.getCountryId(),       // Entity에서 Country 관련 데이터를 가져옵니다.
				entity.getCountryName(),
				entity.getCountryFlag(),
				entity.getLeagueId(),
				entity.getLeagueName(),
				entity.getLeagueLogo()
			))
			.collect(Collectors.toList());
	}

	@Override
	public List<CountryLeagueMappingDTO> getLeaguesBySeasonAndCountryAndLeague(String season, Long countryId,
		Long leagueId) {
		return countryLeagueRepository.findLeaguesBySeasonAndCountryAndLeague(season, countryId, leagueId).stream()
			.map(entity -> new CountryLeagueMappingDTO(
				entity.getLeagueSeason(),
				entity.getCountryName(),
				entity.getCountryFlag(),
				entity.getTeamId(),
				entity.getTeamName(),
				entity.getTeamLogo()
			))
			.collect(Collectors.toList());
	}
}
