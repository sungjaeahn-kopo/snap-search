package com.snap_search.lvalue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap_search.lvalue.model.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

	Player findById(Long id);
}
