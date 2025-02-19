package com.snap_search.lvalue.service;

public interface ImageService {
	void processBatchForCoach(int page) throws Exception;

	void processBatchForLeague(int page) throws Exception;

	void processBatchForTeam(int page) throws Exception;

	void processBatchForPlayer(int page) throws Exception;

	void processBatchForCountry(int page) throws Exception;

	void optimizeFirstLeagueLogo() throws Exception;

	void optimizeFirstTeamLogo() throws Exception;

	void optimizeFirstPlayerPhoto() throws Exception;

	/**
	 * 전체 엔티티의 이미지를 최적화하고 Azure Blob Storage에 업로드 후 DB를 업데이트합니다.
	 */
	void optimizeAndUploadAllImages(int page) throws Exception;

	/**
	 * 특정 이미지 URL을 최적화 및 업로드 후 새로운 URL 반환.
	 */
	String processAndUploadImage(String directory, String imageUrl, int width, int height) throws Exception;
}
