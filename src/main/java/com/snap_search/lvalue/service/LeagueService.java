package com.snap_search.lvalue.service;

import java.util.List;

import com.snap_search.lvalue.model.League;

public interface LeagueService {
	List<League> fetchAndSaveLeagues();
}
