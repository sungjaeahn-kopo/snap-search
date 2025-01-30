package com.snap_search.lvalue.service;

import java.util.List;
import java.util.Optional;

import com.snap_search.lvalue.model.Match;

public interface MatchService {

	List<Match> getMatchesByTeam(int teamId, int season);

	Optional<Match> getMatchById(Long fixtureId);
}
