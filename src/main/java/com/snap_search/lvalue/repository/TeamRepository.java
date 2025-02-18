package com.snap_search.lvalue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap_search.lvalue.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
	List<Integer> findIdByLeagueIds(int leagueId);
}
