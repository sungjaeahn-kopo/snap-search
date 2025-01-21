package com.snap_search.lvalue.service;

import java.util.List;

import com.snap_search.lvalue.model.Player;

public interface PlayerService {
	List<Player> fetchAndSavePlayers();
}
