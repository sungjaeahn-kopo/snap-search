// package com.snap_search.lvalue.service;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// import java.io.ByteArrayOutputStream;
// import java.io.InputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// @SpringBootTest
// public class AzureBlobServiceTest {
//
// 	@Autowired
// 	private AzureBlobServiceImpl azureBlobService;
//
// 	@Test
// 	public void testUploadPngOrSvgFile() throws Exception {
// 		// 테스트할 이미지 URL (예: .svg 파일)
// 		String fileUrl = "https://media.api-sports.io/football/teams/33.png";
// 		// String fileUrl = "https://media.api-sports.io/flags/gb-eng.svg";
//
// 		// URL에서 InputStream 가져오기
// 		InputStream inputStream = downloadFileFromUrl(fileUrl);
//
// 		// InputStream -> ByteArrayOutputStream으로 변환하여 정확한 크기 측정
// 		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
// 		byte[] buffer = new byte[1024];
// 		int bytesRead;
// 		while ((bytesRead = inputStream.read(buffer)) != -1) {
// 			outputStream.write(buffer, 0, bytesRead);
// 		}
//
// 		// ByteArray로 변환
// 		byte[] data = outputStream.toByteArray();
//
// 		// Blob Storage에 업로드
// 		String fileName = "test-image-" + System.currentTimeMillis() + ".svg"; // 파일 확장자는 원본 파일에 맞게 설정
// 		String fileType = "image/png";
// 		// String fileType = "image/svg+xml";
// 		String blobUrl = "";
// 		// azureBlobService.uploadBlob(fileName, new ByteArrayInputStream(data), data.length,
// 		// 	fileType);
//
// 		// 결과 출력 및 검증
// 		System.out.println("Blob URL: " + blobUrl);
// 		assertNotNull(blobUrl);
// 	}
//
// 	// URL에서 파일 다운로드
// 	private InputStream downloadFileFromUrl(String fileUrl) throws Exception {
// 		URL url = new URL(fileUrl);
// 		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
// 		connection.setRequestMethod("GET");
// 		connection.connect();
//
// 		// HTTP 응답 코드 확인
// 		if (connection.getResponseCode() != 200) {
// 			throw new RuntimeException("Failed to download file. HTTP response code: " + connection.getResponseCode());
// 		}
//
// 		// InputStream 반환
// 		return connection.getInputStream();
// 	}
// }
