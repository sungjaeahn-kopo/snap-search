package com.snap_search.lvalue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snap_search.lvalue.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	@Query("SELECT t.id FROM Team t WHERE t.leagueIds IN :leagueIds")
	List<Integer> findTeamIdsByLeagueId(@Param("leagueIds") List<Integer> leagueIds);
}
