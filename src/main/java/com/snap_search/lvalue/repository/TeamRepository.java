package com.snap_search.lvalue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snap_search.lvalue.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	List<Integer> findIdByLeagueIds(int leagueId);
}
