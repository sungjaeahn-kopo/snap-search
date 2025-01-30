package com.snap_search.lvalue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.snap_search.lvalue.model.Match;
import com.snap_search.lvalue.repository.MatchRepository;

@Service
public class MatchServiceImpl implements MatchService {

	private final MatchRepository matchRepository;

	public MatchServiceImpl(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public List<Match> getMatchesByTeam(int teamId, int season) {
		return matchRepository.findByLeagueSeasonAndTeamsHomeIdOrLeagueSeasonAndTeamsAwayId(season, teamId, season,
			teamId);
	}

	public Optional<Match> getMatchById(Long fixtureId) {
		return matchRepository.findById(String.valueOf(fixtureId));
	}

}
