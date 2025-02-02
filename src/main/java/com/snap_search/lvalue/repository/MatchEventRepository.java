package com.snap_search.lvalue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snap_search.lvalue.model.MatchEvents;

@Repository
public interface MatchEventRepository extends JpaRepository<MatchEvents, Long> {
	List<MatchEvents> findByFixtureIdOrderByTimeElapsed(Long fixtureId);
}
