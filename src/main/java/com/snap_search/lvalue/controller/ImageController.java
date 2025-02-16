package com.snap_search.lvalue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@GetMapping("/optimize/league/first")
	public ResponseEntity<String> optimizeFirstLeagueLogo() {
		try {
			imageService.optimizeFirstLeagueLogo();
			return ResponseEntity.ok("First league logo optimized successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error optimizing league logo: " + e.getMessage());
		}
	}

	@GetMapping("/optimize/team/first")
	public ResponseEntity<String> optimizeFirstTeamLogo() {
		try {
			imageService.optimizeFirstTeamLogo();
			return ResponseEntity.ok("First team logo optimized successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error optimizing team logo: " + e.getMessage());
		}
	}

	@GetMapping("/optimize/player/first")
	public ResponseEntity<String> optimizeFirstPlayerPhoto() {
		try {
			imageService.optimizeFirstPlayerPhoto();
			return ResponseEntity.ok("First player photo optimized successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error optimizing player photo: " + e.getMessage());
		}
	}

	/**
	 * 전체 이미지 최적화 및 업로드를 트리거합니다.
	 */
	// @GetMapping("/optimize")
	// public ResponseEntity<String> optimizeAndUploadAllImages(int page) {
	// 	try {
	// 		imageService.optimizeAndUploadAllImages(page);
	// 		return ResponseEntity.ok("All images processed and uploaded successfully.");
	// 	} catch (Exception e) {
	// 		return ResponseEntity.status(500).body("Error processing images: " + e.getMessage());
	// 	}
	// }
	@GetMapping("/optimize/league/batch")
	public ResponseEntity<String> optimizeLeagueBatch(@RequestParam int page) {
		try {
			imageService.processBatchForLeague(page);
			return ResponseEntity.ok("League batch processed successfully for page: " + page);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error processing league batch: " + e.getMessage());
		}
	}

	@GetMapping("/optimize/team/batch")
	public ResponseEntity<String> optimizeTeamBatch(@RequestParam int page) {
		try {
			imageService.processBatchForTeam(page);
			return ResponseEntity.ok("Team batch processed successfully for page: " + page);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error processing team batch: " + e.getMessage());
		}
	}

	@GetMapping("/optimize/player/batch")
	public ResponseEntity<String> optimizePlayerBatch(@RequestParam int page) {
		try {
			imageService.processBatchForPlayer(page);
			return ResponseEntity.ok("Player batch processed successfully for page: " + page);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error processing player batch: " + e.getMessage());
		}
	}

	/**
	 * 특정 이미지 URL을 최적화 및 업로드합니다.
	 * @param imageUrl 업로드할 이미지 URL
	 * @param type 이미지 종류 (league, team, player)
	 * @return 최적화된 Blob URL
	 */
	@GetMapping("/optimize/single")
	public ResponseEntity<String> optimizeAndUploadSingleImage(
		@RequestParam String imageUrl,
		@RequestParam String type
	) {
		try {
			String directory;
			int width, height;

			// 종류별 디렉토리 및 크기 설정
			switch (type.toLowerCase()) {
				case "league":
					directory = "league-logos";
					width = 100;
					height = 100;
					break;
				case "team":
					directory = "team-logos";
					width = 100;
					height = 100;
					break;
				case "player":
					directory = "player-photos";
					width = 150;
					height = 150;
					break;
				default:
					return ResponseEntity.badRequest().body("Invalid image type: " + type);
			}

			String blobUrl = imageService.processAndUploadImage(directory, imageUrl, width, height);
			return ResponseEntity.ok("Optimized Blob URL: " + blobUrl);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error processing image: " + e.getMessage());
		}
	}
}
