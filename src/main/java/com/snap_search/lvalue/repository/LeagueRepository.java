package com.snap_search.lvalue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap_search.lvalue.model.League;

public interface LeagueRepository extends JpaRepository<League, String> {

	League findById(Long id);
}
