package com.snap_search.lvalue.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.snap_search.lvalue.service.ImageService;

@Component
public class ImageBatchScheduler {

	@Autowired
	private ImageService imageService;

	private int leaguePage = 0;
	private int teamPage = 0;
	private int playerPage = 0;

	@Scheduled(fixedRate = 3600000) // 1시간마다 실행
	public void processLeagueBatch() {
		try {
			imageService.processBatchForLeague(leaguePage);
			leaguePage++;
		} catch (Exception e) {
			System.err.println("Error processing league batch: " + e.getMessage());
		}
	}

	@Scheduled(fixedRate = 3600000, initialDelay = 600000) // 1시간마다, 10분 딜레이 후 실행
	public void processTeamBatch() {
		try {
			imageService.processBatchForTeam(teamPage);
			teamPage++;
		} catch (Exception e) {
			System.err.println("Error processing team batch: " + e.getMessage());
		}
	}

	@Scheduled(fixedRate = 3600000, initialDelay = 1200000) // 1시간마다, 20분 딜레이 후 실행
	public void processPlayerBatch() {
		try {
			imageService.processBatchForPlayer(playerPage);
			playerPage++;
		} catch (Exception e) {
			System.err.println("Error processing player batch: " + e.getMessage());
		}
	}
}

