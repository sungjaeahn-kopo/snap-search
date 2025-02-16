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
	private int countryPage = 0;

	private static final int BATCH_INTERVAL = 600000;
	private static final int INITIAL_DELAY = 30000;

	@Scheduled(fixedRate = BATCH_INTERVAL, initialDelay = INITIAL_DELAY)
	public void processLeagueBatch() {
		try {
			imageService.processBatchForLeague(leaguePage);
			leaguePage++;
		} catch (Exception e) {
			System.err.println("Error processing league batch: " + e.getMessage());
		}
	}

	@Scheduled(fixedRate = BATCH_INTERVAL, initialDelay = INITIAL_DELAY + 10000)
	public void processTeamBatch() {
		try {
			imageService.processBatchForTeam(teamPage);
			teamPage++;
		} catch (Exception e) {
			System.err.println("Error processing team batch: " + e.getMessage());
		}
	}

	@Scheduled(fixedRate = BATCH_INTERVAL, initialDelay = INITIAL_DELAY + 20000)
	public void processPlayerBatch() {
		try {
			imageService.processBatchForPlayer(playerPage);
			playerPage++;
		} catch (Exception e) {
			System.err.println("Error processing player batch: " + e.getMessage());
		}
	}

	@Scheduled(fixedRate = BATCH_INTERVAL, initialDelay = INITIAL_DELAY + 30000)
	public void processCountryBatch() {
		try {
			imageService.processBatchForCountry(countryPage);
			countryPage++;
		} catch (Exception e) {
			System.err.println("Error processing country batch: " + e.getMessage());
		}
	}
}

