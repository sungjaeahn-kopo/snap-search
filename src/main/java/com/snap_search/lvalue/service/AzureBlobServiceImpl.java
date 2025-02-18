package com.snap_search.lvalue.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;

@Service
public class AzureBlobServiceImpl implements AzureBlobService {

	@Value("${azure.storage.connection-string}")
	private String connectionString;

	@Value("${azure.storage.container-name}")
	private String containerName;

	// Blob 컨테이너 클라이언트 생성
	private BlobContainerClient getContainerClient() {
		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
			.connectionString(connectionString)
			.buildClient();
		return blobServiceClient.getBlobContainerClient(containerName);
	}

	// Blob 업로드 메서드 구현
	@Override
	public String uploadBlob(String directoryPath, String fileName, InputStream inputStream, long fileSize,
		String contentType) {
		try {
			System.out.println("===== start uploadBlob =====");
			BlobContainerClient containerClient = getContainerClient();
			BlobClient blobClient = containerClient.getBlobClient(directoryPath + fileName);

			blobClient.upload(inputStream, fileSize, true);

			BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(contentType);
			blobClient.setHttpHeaders(headers);

			// 업로드된 Blob URL 반환
			return blobClient.getBlobUrl();
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload blob: " + e.getMessage(), e);
		}
	}
}
