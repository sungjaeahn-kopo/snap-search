package com.snap_search.lvalue.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.snap_search.lvalue.repository.TeamRepository;
import com.snap_search.lvalue.service.MatchService;

@Component
public class MatchBatchScheduler {

	private static final Logger logger = LoggerFactory.getLogger(MatchBatchScheduler.class);
	@Autowired
	private MatchService matchService;

	@Autowired
	private TeamRepository teamRepository;

	// 적용 대상 리그 ID 리스트
	private static final List<Integer> TARGET_LEAGUES = List.of(39, 61, 78, 140);

	/**
	 * 매일 00:00 기준으로 업데이트가 필요한 경기 정보를 가져와 업데이트하는 배치 작업
	 */
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
	public void fetchMatchDataBatch() {
		logger.info("[Match Batch] 경기 데이터 업데이트 시작");

		for (int leagueId : TARGET_LEAGUES) {
			List<Integer> teamIds = teamRepository.findIdByLeagueIds(leagueId);

			for (int teamId : teamIds) {
				try {
					matchService.fetchMatcheData(teamId);
					System.out.println("Match batch completed for teamId: " + teamId);
				} catch (Exception e) {
					System.err.println("Error processing match batch for teamId: " + teamId + " - " + e.getMessage());
				}
			}
		}
	}
}

