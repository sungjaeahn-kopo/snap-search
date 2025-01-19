package com.snap_search.lvalue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snap_search.lvalue.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, String> {

	@Query(value = """
		    SELECT 
		        c.id, c.name, c.age, c.nationality, c.photo,
		        c.career_0_team_id, c.career_0_team_name, c.career_0_team_logo, c.career_0_start, c.career_0_end,
		        c.career_1_team_id, c.career_1_team_name, c.career_1_team_logo, c.career_1_start, c.career_1_end,
		        c.career_2_team_id, c.career_2_team_name, c.career_2_team_logo, c.career_2_start, c.career_2_end,
		        c.career_3_team_id, c.career_3_team_name, c.career_3_team_logo, c.career_3_start, c.career_3_end,
		        c.career_4_team_id, c.career_4_team_name, c.career_4_team_logo, c.career_4_start, c.career_4_end,
		        c.career_5_team_id, c.career_5_team_name, c.career_5_team_logo, c.career_5_start, c.career_5_end,
		        c.career_6_team_id, c.career_6_team_name, c.career_6_team_logo, c.career_6_start, c.career_6_end,
		        c.career_7_team_id, c.career_7_team_name, c.career_7_team_logo, c.career_7_start, c.career_7_end,
		        c.career_8_team_id, c.career_8_team_name, c.career_8_team_logo, c.career_8_start, c.career_8_end,
		        c.career_9_team_id, c.career_9_team_name, c.career_9_team_logo, c.career_9_start, c.career_9_end,
		        c.career_10_team_id, c.career_10_team_name, c.career_10_team_logo, c.career_10_start, c.career_10_end,
		        c.career_11_team_id, c.career_11_team_name, c.career_11_team_logo, c.career_11_start, c.career_11_end,
		        c.career_12_team_id, c.career_12_team_name, c.career_12_team_logo, c.career_12_start, c.career_12_end,
		        c.career_13_team_id, c.career_13_team_name, c.career_13_team_logo, c.career_13_start, c.career_13_end,
		        c.career_14_team_id, c.career_14_team_name, c.career_14_team_logo, c.career_14_start, c.career_14_end,
		        c.career_15_team_id, c.career_15_team_name, c.career_15_team_logo, c.career_15_start, c.career_15_end,
		        c.career_16_team_id, c.career_16_team_name, c.career_16_team_logo, c.career_16_start, c.career_16_end,
		        c.career_17_team_id, c.career_17_team_name, c.career_17_team_logo, c.career_17_start, c.career_17_end,
		        c.career_18_team_id, c.career_18_team_name, c.career_18_team_logo, c.career_18_start, c.career_18_end,
		        c.career_19_team_id, c.career_19_team_name, c.career_19_team_logo, c.career_19_start, c.career_19_end,
		        c.career_20_team_id, c.career_20_team_name, c.career_20_team_logo, c.career_20_start, c.career_20_end,
		        c.career_21_team_id, c.career_21_team_name, c.career_21_team_logo, c.career_21_start, c.career_21_end,
		        c.career_22_team_id, c.career_22_team_name, c.career_22_team_logo, c.career_22_start, c.career_22_end,
		        c.career_23_team_id, c.career_23_team_name, c.career_23_team_logo, c.career_23_start, c.career_23_end,
		        c.career_24_team_id, c.career_24_team_name, c.career_24_team_logo, c.career_24_start, c.career_24_end,
		        c.career_25_team_id, c.career_25_team_name, c.career_25_team_logo, c.career_25_start, c.career_25_end
		    FROM coaches c
		    WHERE c.team_id = :teamId
		      AND c.career_0_end = ''
		    ORDER BY c.career_0_start DESC
		    LIMIT 1
		""", nativeQuery = true)
	Optional<Object> findCoachInfoByteamId(@Param("teamId") Long teamId);
}
