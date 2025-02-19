package com.snap_search.lvalue.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snap_search.lvalue.model.Coach;
import com.snap_search.lvalue.model.Country;
import com.snap_search.lvalue.model.League;
import com.snap_search.lvalue.model.Player;
import com.snap_search.lvalue.model.Team;
import com.snap_search.lvalue.repository.CoachRepository;
import com.snap_search.lvalue.repository.CountryRepository;
import com.snap_search.lvalue.repository.LeagueRepository;
import com.snap_search.lvalue.repository.PlayerRepository;
import com.snap_search.lvalue.repository.TeamRepository;
import com.snap_search.lvalue.util.ImageOptimizationUtil;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private AzureBlobService azureBlobService;

	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private CountryRepository countryRepository;

	private static final int BATCH_SIZE = 100;

	@Override
	@Transactional
	public void processBatchForCoach(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, BATCH_SIZE);
		List<Coach> coaches = coachRepository.findAll(pageable).getContent();
		List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});

		for (Coach coach : coaches) {
			if (isFromOriginalDomain(coach.getPhoto())) {
				String updatedUrl = processAndUploadMultipleSizes("coach-logos", coach.getPhoto(), sizes, "image/png");
				coach.setPhoto(updatedUrl);
			} else {
				// 이미 처리된 경우 로그로 남김
				System.out.println("No update required for league ID: " + coach.getId());
			}

			for (int i = 0; i <= 25; i++) {
				String careerTeamIdField = "career_" + i + "_team_id";
				String careerTeamLogoField = "career_" + i + "_team_logo";

				Integer careerTeamId = getCareerTeamId(coach, careerTeamIdField); // 리플렉션을 사용하여 teamId 가져오기
				String currentLogo = getCareerTeamLogo(coach, careerTeamLogoField);

				System.err.println("upload logo for " + careerTeamId + ": " + currentLogo);
				if (isFromOriginalDomain(currentLogo)) {
					try {
						String updatedLogoUrl = processAndUploadMultipleSizes("team-logos", currentLogo, sizes,
							"image/png");
						setCareerTeamLogo(coach, careerTeamLogoField, updatedLogoUrl); // 리플렉션을 사용하여 업데이트
					} catch (Exception e) {
						System.err.println("Failed to upload logo for " + careerTeamIdField + ": " + e.getMessage());
					}
				}
			}
		}

		coachRepository.saveAll(coaches);
	}

	@Override
	@Transactional
	public void processBatchForLeague(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, BATCH_SIZE);
		List<League> leagues = leagueRepository.findAll(pageable).getContent();

		for (League league : leagues) {
			String currentLogo = league.getLogo();

			if (isFromOriginalDomain(currentLogo)) {
				List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});
				String updatedUrl = processAndUploadMultipleSizes("league-logos", currentLogo, sizes, "image/png");
				league.setLogo(updatedUrl);
			} else {
				// 이미 처리된 경우 로그로 남김
				System.out.println("No update required for league ID: " + league.getId());
			}
		}

		leagueRepository.saveAll(leagues);
	}

	@Override
	@Transactional
	public void processBatchForTeam(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, BATCH_SIZE);
		List<Team> teams = teamRepository.findAll(pageable).getContent();

		for (Team team : teams) {
			String currentLogo = team.getLogo();

			if (isFromOriginalDomain(currentLogo)) {
				List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});
				String updatedUrl = null;
				try {
					updatedUrl = processAndUploadMultipleSizes("team-logos", currentLogo, sizes, "image/png");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				team.setLogo(updatedUrl);
			} else {
				// 이미 처리된 경우 로그로 남김
				System.out.println("No update required for team ID: " + team.getId() + " " + team.getName());
			}
		}

		teamRepository.saveAll(teams);
	}

	@Override
	@Transactional
	public void processBatchForPlayer(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, BATCH_SIZE);
		List<Player> players = playerRepository.findAll(pageable).getContent();

		for (Player player : players) {
			String currentLogo = player.getPhoto();

			if (isFromOriginalDomain(currentLogo)) {
				List<int[]> sizes = Arrays.asList(new int[] {150, 150}, new int[] {80, 80});
				String updatedUrl = processAndUploadMultipleSizes("player-photos", player.getPhoto(), sizes,
					"image/png");
				player.setPhoto(updatedUrl);
			} else {
				// 이미 처리된 경우 로그로 남김
				System.out.println("No update required for player ID: " + player.getId());
			}
		}

		playerRepository.saveAll(players);
	}

	@Override
	@Transactional
	public void processBatchForCountry(int page) throws Exception {
		Pageable pageable = PageRequest.of(page, BATCH_SIZE);
		List<Country> countries = countryRepository.findAll(pageable).getContent();

		for (Country country : countries) {
			String currentLogo = country.getFlag();

			if (isFromOriginalDomain(currentLogo)) {
				List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});
				String updatedUrl = processAndUploadMultipleSizes("country-photos", currentLogo, sizes,
					"image/svg+xml");
				country.setFlag(updatedUrl);
			} else {
				// 이미 처리된 경우 로그로 남김
				System.out.println("No update required for player ID: " + country.getId());
			}
		}

		countryRepository.saveAll(countries);
	}

	@Override
	@Transactional
	public void optimizeAndUploadAllImages(int page) throws Exception {
		// League 처리
		Pageable pageable = PageRequest.of(page, BATCH_SIZE);
		List<League> leagues = leagueRepository.findAll(pageable).getContent();
		for (League league : leagues) {
			if (league.getLogo() != null) {
				List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});
				String updatedUrl = processAndUploadMultipleSizes("league-logos", league.getLogo(), sizes, "image/png");
				league.setLogo(updatedUrl); // 기본 크기 URL 설정
			}
		}

		// Team 처리
		List<Team> teams = teamRepository.findAll();
		for (Team team : teams) {
			if (team.getLogo() != null) {
				List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});
				String updatedUrl = processAndUploadMultipleSizes("team-logos", team.getLogo(), sizes, "image/png");
				team.setLogo(updatedUrl); // 기본 크기 URL 설정
			}
		}

		// Player 처리
		List<Player> players = playerRepository.findAll();
		for (Player player : players) {
			if (player.getPhoto() != null) {
				List<int[]> sizes = Arrays.asList(new int[] {150, 150}, new int[] {50, 50});
				String updatedUrl = processAndUploadMultipleSizes("player-photos", player.getPhoto(), sizes,
					"image/png");
				player.setPhoto(updatedUrl); // 기본 크기 URL 설정
			}
		}

		// DB 저장
		leagueRepository.saveAll(leagues);
		teamRepository.saveAll(teams);
		playerRepository.saveAll(players);
	}

	@Override
	@Transactional
	public void optimizeFirstLeagueLogo() throws Exception {
		League league = leagueRepository.findAll().stream().findFirst().orElse(null);
		if (league != null && league.getLogo() != null) {
			List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});
			String updatedUrl = processAndUploadMultipleSizes("league-logos", league.getLogo(), sizes, "image/png");
			league.setLogo(updatedUrl); // 기본적으로 100x100 크기의 URL을 설정
			leagueRepository.save(league);
			System.out.println("Optimized League Logo: " + updatedUrl);
		}
	}

	@Override
	@Transactional
	public void optimizeFirstTeamLogo() throws Exception {
		Team team = teamRepository.findAll().stream().findFirst().orElse(null);
		if (team != null && team.getLogo() != null) {
			List<int[]> sizes = Arrays.asList(new int[] {100, 100}, new int[] {35, 35});
			String updatedUrl = processAndUploadMultipleSizes("team-logos", team.getLogo(), sizes, "image/png");
			team.setLogo(updatedUrl); // 기본적으로 100x100 크기의 URL을 설정
			teamRepository.save(team);
			System.out.println("Optimized Team Logo: " + updatedUrl);
		}
	}

	@Override
	@Transactional
	public void optimizeFirstPlayerPhoto() throws Exception {
		Player player = playerRepository.findAll().stream().findFirst().orElse(null);
		if (player != null && player.getPhoto() != null) {
			List<int[]> sizes = Arrays.asList(new int[] {150, 150}, new int[] {50, 50});
			String updatedUrl = processAndUploadMultipleSizes("player-photos", player.getPhoto(), sizes, "image/png");
			player.setPhoto(updatedUrl); // 기본적으로 150x150 크기의 URL을 설정
			playerRepository.save(player);
			System.out.println("Optimized Player Photo: " + updatedUrl);
		}
	}

	@Override
	public String processAndUploadImage(String directory, String imageUrl, int width, int height) throws Exception {
		// 원본 이미지 다운로드
		InputStream originalImage = downloadImageFromUrl(imageUrl);

		// 이미지 최적화 (리사이즈 및 WebP 변환)
		InputStream optimizedImage = ImageOptimizationUtil.resizeImage(originalImage, width, height);

		// 사이즈 디렉토리 구성
		String sizeDirectory = String.format("%s/%dx%d", directory, width, height);

		// 파일 이름 추출 및 WebP 확장자 적용
		String fileName = extractFileNameFromUrl(imageUrl) + ".webp";

		// Azure Blob Storage에 업로드
		return azureBlobService.uploadBlob(sizeDirectory + "/", fileName, optimizedImage, optimizedImage.available(),
			"image/webp");
	}

	private String processAndUploadMultipleSizes(String directory, String imageUrl, List<int[]> sizes,
		String contentType) throws Exception {
		InputStream originalImage = downloadImageFromUrl(imageUrl);
		byte[] originalImageBytes = originalImage.readAllBytes(); // InputStream 데이터를 메모리에 저장

		String baseFileName = extractFileNameFromUrl(imageUrl);
		String baseBlobUrl = null;

		for (int[] size : sizes) {
			int width = size[0];
			int height = size[1];

			// 메모리에 저장된 원본 데이터를 새로운 InputStream으로 생성
			InputStream imageStream = new ByteArrayInputStream(originalImageBytes);

			InputStream resizedImage = "image/svg+xml".equals(contentType) ? imageStream :
				ImageOptimizationUtil.resizeImage(imageStream, width, height);
			String sizeString = String.format("%dx%d", width, height);
			String sizeDirectory = String.format("%s/%s", directory, sizeString);
			String fileName = baseFileName;

			System.out.println("=== blob update === " + imageUrl);
			// Blob Storage에 업로드
			String blobUrl = azureBlobService.uploadBlob(sizeDirectory + "/", fileName, resizedImage,
				resizedImage.available(), contentType);

			// 기본 URL 설정 (사이즈 디렉토리 제외)
			if (baseBlobUrl == null) {
				String decodedUrl = URLDecoder.decode(blobUrl, StandardCharsets.UTF_8);
				baseBlobUrl = decodedUrl.replace("/" + sizeString, "");
			}
		}

		return baseBlobUrl;
	}

	private InputStream downloadImageFromUrl(String imageUrl) throws Exception {
		URL url = new URL(imageUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed to download image. HTTP response code: " + connection.getResponseCode());
		}

		return connection.getInputStream();
	}

	private String extractFileNameFromUrl(String imageUrl) {
		return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	}

	private boolean isFromOriginalDomain(String logoUrl) {
		return logoUrl != null && logoUrl.contains("media.api-sports.io");
	}

	private Integer getCareerTeamId(Coach coach, String fieldName) {
		try {
			Field field = Coach.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			return (Integer)field.get(coach);
		} catch (Exception e) {
			System.err.println("Error getting field " + fieldName + ": " + e.getMessage());
			return null;
		}
	}

	private String getCareerTeamLogo(Coach coach, String fieldName) {
		try {
			Field field = Coach.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			return (String)field.get(coach);
		} catch (Exception e) {
			System.err.println("Error getting field " + fieldName + ": " + e.getMessage());
			return null;
		}
	}

	private void setCareerTeamLogo(Coach coach, String fieldName, String value) {
		try {
			Field field = Coach.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(coach, value);
		} catch (Exception e) {
			System.err.println("Error setting field " + fieldName + ": " + e.getMessage());
		}
	}

}