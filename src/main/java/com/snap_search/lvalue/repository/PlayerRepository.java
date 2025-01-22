package com.snap_search.lvalue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snap_search.lvalue.model.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

	@Query(value = "SELECT * FROM player p WHERE p.id = :id ORDER BY p.id ASC LIMIT 1", nativeQuery = true)
	Optional<Player> findById(@Param("id") Long id);
}
